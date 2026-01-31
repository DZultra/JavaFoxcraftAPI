package net.dzultra.jfa.types.leaderboards;

import net.dzultra.jfa.types.Gamemode;

public interface LeaderboardType {
    Gamemode getGamemode();
    Class<? extends LeaderboardType> getInstance();
    String getTitle();
}
