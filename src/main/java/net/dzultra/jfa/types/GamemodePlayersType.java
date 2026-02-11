package net.dzultra.jfa.types;

public enum GamemodePlayersType {
    KINGDOMS(Gamemode.KINGDOMS),
    SURVIVAL(Gamemode.SURVIVAL);

    private final Gamemode gamemode;

    GamemodePlayersType(Gamemode gamemode) {
        this.gamemode = gamemode;
    }

    public Gamemode getGamemode() {
        return gamemode;
    }
}
