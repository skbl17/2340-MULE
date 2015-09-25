package game;

import java.io.IOException;

import game.view.ScreenStackController;
import game.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ScreenStackController screenStack;

    private final String MAIN = "/game/view/MainScreen.fxml";
    private final String PLAYER_CONFIG = "/game/view/PlayerConfig.fxml";
    private final String MAP = "/game/view/Map.fxml";
    private final String TOWN = "/game/view/Town.fxml";
    private final String STORE = "/game/view/Store.fxml";

    private static Main main;
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE Game");
        this.primaryStage.getIcons().add(new Image("/game/images/icon.png"));

        main = this;
        screenStack = new ScreenStackController();

        screenStack.loadScreen("main", MAIN);
        screenStack.loadScreen("player config", PLAYER_CONFIG);

        initRootLayout();
        showMainScreen();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("/game/view/RootLayout.fxml"));
            rootLayout = loader.load();
            rootLayout.setCenter(screenStack);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() { return main; }

    public void newGame(int playerCount, boolean isRandom) {
        game = new Game(playerCount, isRandom);

        screenStack.loadScreen("town",  TOWN);
        screenStack.loadScreen("store", STORE);
    }

    public void generateMap() {
        game.startGame();
        screenStack.loadScreen("map", MAP);
    }

    public void showMainScreen() {
        screenStack.setScreen("main");
    }

    public void showConfigScreen() {
        screenStack.setScreen("player config");
    }

    public void showMap() {
        screenStack.setScreen("map");
    }

    public void showTown() {
        screenStack.setScreen("town");
    }

    public void showStore() {
        screenStack.setScreen("store");
    }

    public void closeScreen() {
        screenStack.removeTop();
    }

    public Game getGame() {
        return game;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
