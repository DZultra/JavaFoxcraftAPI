package net.dzultra.jfa.types;

public interface LeaderboardType {
    Gamemode getGamemode();
    Class<? extends LeaderboardType> getInstance();
    String getTitle();
}
