package net.dzultra.jfa.types.leaderboards;

import net.dzultra.jfa.types.Gamemode;

public enum ParkourLeaderboards implements LeaderboardType {
    PARKOUR_POINTS_GAINED("Parkour points gained"),
    PARKOUR_SHARDS_GAINED("Parkour shards gained"),
    PARKOUR_XP_GAINED("Parkour XP gained"),
    PARKOURS_COMPLETED("Parkours completed"),
    PARKOURS_PUBLISHED("Parkours published"),
    JUMPS("Jumps"),
    BLOCKS_PLACED("Blocks placed");

    private final String title;

    ParkourLeaderboards(String name) {
        this.title = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Gamemode getGamemode() {
        return Gamemode.PARKOUR;
    }

    @Override
    public Class<ParkourLeaderboards> getInstance() {
        return ParkourLeaderboards.class;
    }
}
