package net.dzultra.jfa.types.leaderboards;

import net.dzultra.jfa.types.Gamemode;
import net.dzultra.jfa.types.LeaderboardType;

public enum OneblockLeaderboards implements LeaderboardType {
    KILLS("Kills"),
    DEATHS("Deaths"),
    FISH_CAUGHT("Fish caught"),
    MONEY_EARNED("Money earned"),
    WARPS_VISITED("Warps visited");

    private final String title;

    OneblockLeaderboards(String name) {
        this.title = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Gamemode getGamemode() {
        return Gamemode.ONEBLOCK;
    }

    @Override
    public Class<OneblockLeaderboards> getInstance() {
        return OneblockLeaderboards.class;
    }
}
