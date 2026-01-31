package net.dzultra.jfa.types.leaderboards;

import net.dzultra.jfa.types.Gamemode;
import net.dzultra.jfa.types.LeaderboardType;

public enum SurvivalLeaderboards implements LeaderboardType {
    KILLS("Kills"),
    BLOCKS_PLACED("Blocks placed"),
    MONEY_EARNED("Money earned"),
    WARPS_VISITED("Warps visited");

    private final String title;

    SurvivalLeaderboards(String name) {
        this.title = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Gamemode getGamemode() {
        return Gamemode.SURVIVAL;
    }

    @Override
    public Class<SurvivalLeaderboards> getInstance() {
        return SurvivalLeaderboards.class;
    }
}
