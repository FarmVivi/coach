package fr.farmvivi.coach.command;

public enum CommandCategory {
    OTHER("Autres");

    private String name;

    CommandCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
