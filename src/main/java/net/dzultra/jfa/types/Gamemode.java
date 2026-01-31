package net.dzultra.jfa.types;

@SuppressWarnings("unused")
public enum Gamemode {
    ONEBLOCK("oneblock"),
    SURVIVAL("survival"),
    KINGDOMS("kingdoms"),
    PARKOUR("parkour");

    private final String name;

    Gamemode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
