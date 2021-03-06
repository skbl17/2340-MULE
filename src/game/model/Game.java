package game.model;

public class Game {
    Turn currentTurn;
    int playerCounter, roundCounter, passCounter, phase, timeLeft;
    String gameLog;

    Player[] players, playerOrder;
    int currentId;

    Map map;
    Store store;

    public Game(int playerCount, String mapType) {
        this.players = new Player[playerCount];
        this.playerOrder = new Player[playerCount];
        store = new Store();
        this.map = new Map(mapType);
        map.setGame(this);
    }

    public void startGame() {
        playerCounter = 0;
        roundCounter = 0;
        passCounter = 0;
        phase = 0;
        gameLog = "Welcome to MULE Game.\n";

        currentId = 0;
        currentTurn = new Turn(players[currentId], this);
    }

    public void newPlayer(int playerIndex, String name, String color,
                          Race race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(playerIndex, name, color, race);
            playerOrder[playerIndex] = players[playerIndex];
        }
    }

    public void endTurn() {
        if (playerCounter < players.length - 1) {
            playerCounter++;
        } else {
            reorderPlayers();
            playerCounter = 0;
            roundCounter++;
        }

        if (roundCounter <= 1) {
            phase = 0;
        } else if ((roundCounter > 1) && (passCounter < players.length &&
                phase <= 1)) {
            phase = 1;
        } else {
            phase = 2;
        }

        if (currentId == 0) {
            passCounter = 0;
        }

        currentId = playerOrder[playerCounter].getId();
        currentTurn = new Turn(playerOrder[playerCounter], this);
    }

    public void passTurn() {
        passCounter++;
        endTurn();
    }

    public int getPhase() {
        return phase;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Player getCurrentPlayer() {
        return playerOrder[playerCounter];
    }

    public Player getPlayer(int id) { return players[id]; }

    public Turn getTurn() {
        return currentTurn;
    }

    public Map getMap() {
        return map;
    }

    public int getPlayerCount() {
        return players.length;
    }

    public Store getStore() {
        return store;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getGameLog() {
        return gameLog;
    }

    public void logEvent(String event) {
        gameLog += event + "\n";
    }

    public String getLeaderBoard() {
        String leaderBoard = "";
        int index = 0;

        for (Player p : players) {
            leaderBoard += "Player " + (index + 1) + ": " + p.getScore() + "\n";
            index++;
        }

        return leaderBoard;
    }

    public void reorderPlayers() {
        for (int i = 0; i < playerOrder.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < playerOrder.length; j++) {

                if (playerOrder[i].getScore() < playerOrder[j].getScore()) {
                    maxIndex = j;
                }
            }
            Player temp = playerOrder[i];
            playerOrder[i] = playerOrder[maxIndex];
            playerOrder[maxIndex] = temp;

        }
    }
}
