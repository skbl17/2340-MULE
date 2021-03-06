package game.model;

import java.util.HashMap;

/**
 */
public class Player {
    String name, color;
    Race race;
    int id, properties, mule;
    HashMap<String, Integer> resources;

    public Player() {
    }

    public Player(int id, String name, String color, Race race) {
        resources = new HashMap<String, Integer>();
        this.id = id;
        this.name = name;
        this.color = color;
        this.race = race;
        if (race == Race.FLAPPER) {
            resources.put("money", 1600);
        } else if (race == Race.HUMAN) {
            resources.put("money", 600);
        } else {
            resources.put("money", 1000);
        }
        resources.put("food", 8);
        resources.put("energy", 4);
        resources.put("smithore", 0);
        resources.put("crystite", 0);
    }

    public int getId() { return id; }
    public String getName() {return this.name; }
    public String getColor() { return this.color; }
    public Race getRace() { return this.race; }

    public int get(String resource) {
        return resources.get(resource);
    }
    public void set(String resource, int amount) {
        resources.replace(resource, amount);
    }
    public int getMule() { return mule; }


    public void setMule(int mule) { this.mule = mule; }

    public int getScore() {
        return this.properties * 500 + resources.get("money") + resources.get("energy") * 25 +
                resources.get("smithore") * 50 + resources.get("food") * 30;
    }

    public String getResourceString() {
        String resourceString = "";
        resourceString += "Money: " + resources.get("money") +
                "\t\tSmithore: " + resources.get("smithore") + "\n";
        resourceString += "Food: " + resources.get("food") + "\t\t\tCrystite: " + resources.get("crystite") +
                "\n";
        resourceString += "Energy: " + resources.get("energy") + "\t\t\tMule: " + mule +
                "\n";

        return resourceString;
    }

    public void addProperty() {
        this.properties++;
    }

}
