package net.dzultra.jfa.types;

@SuppressWarnings("unused")
public enum Gamemode {
    ONEBLOCK("oneblock"),
    SURVIVAL("survival"),
    KINGDOMS("kingdoms"),
    PARKOUR("parkour"),
    SKYBLOCK("skyblock"),
    PRISON("prison"),
    CREATIVE("creative"),
    HUB("hub"),
    HIDDEN("hidden"),
    OFFLINE("offline"),
    RED_VS_BLUE("rvb");

    private final String name;

    Gamemode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Gamemode getGamemodeByName(String name) {
        if (name == null) return null;
        for (Gamemode gamemode : values()) {
            if (gamemode.name.equalsIgnoreCase(name) || gamemode.name().equalsIgnoreCase(name)) {
                return gamemode;
            }
        }
        return null;
    }
}
