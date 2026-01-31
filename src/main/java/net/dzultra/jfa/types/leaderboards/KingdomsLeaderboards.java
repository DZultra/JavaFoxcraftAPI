package net.dzultra.jfa.types.leaderboards;

import net.dzultra.jfa.types.Gamemode;

public enum KingdomsLeaderboards implements LeaderboardType {
    BLOCKS_BROKEN("Blocks broken"),
    BLOCKS_PLACED("Blocks placed"),
    DEATHS("Deaths"),
    KILLS("Kills"),
    FISH_CAUGHT("Fish caught"),
    MOB_KILLS("Mob kills");

    private final String title;

    KingdomsLeaderboards(String name) {
        this.title = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Gamemode getGamemode() {
        return Gamemode.KINGDOMS;
    }

    @Override
    public Class<KingdomsLeaderboards> getInstance() {
        return KingdomsLeaderboards.class;
    }
}
