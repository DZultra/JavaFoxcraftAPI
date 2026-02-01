package net.dzultra.jfa.apidata;

import net.dzultra.jfa.types.statistics.*;
import net.dzultra.jfa.types.Gamemode;
import org.jetbrains.annotations.Nullable;

public record PlayerStats(
        String username,
        String headUrl,
        boolean isOnline,
        boolean isBedrock,
        boolean isHidden,
        @Nullable Gamemode currentGamemode,
        @Nullable Badge badge,
        String lastSeen,
        OneblockStats oneblockStats,
        SurvivalStats survivalStats,
        SkyblockStats skyblockStats,
        KingdomsStats kingdomsStats,
        ParkourStats parkourStats,
        CreativeStats creativeStats,
        PrisonStats prisonStats
) {
    public record OneblockStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            int blocksBroken,
            int blocksPlaced,
            int itemsCrafted,
            int toolsBroken,
            int highestKillstreak,
            int playersKilled,
            int deaths,
            int arrowsShot,
            DistanceTraveledStat distanceTraveled
    ) implements ServerStats{}

    public record SurvivalStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            int blocksBroken,
            int blocksPlaced,
            int itemsCrafted,
            int toolsBroken,
            int highestKillstreak,
            int playersKilled,
            int deaths,
            int arrowsShot,
            DistanceTraveledStat distanceTraveled
    ) implements ServerStats {}

    public record SkyblockStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            int blocksBroken,
            int blocksPlaced,
            int itemsCrafted,
            int toolsBroken,
            int highestKillstreak,
            int playersKilled,
            int deaths,
            int arrowsShot,
            DistanceTraveledStat distanceTraveled
    ) implements ServerStats {}

    public record KingdomsStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            int blocksBroken,
            int blocksPlaced,
            int itemsCrafted,
            int toolsBroken,
            int highestKillstreak,
            int playersKilled,
            int deaths,
            int arrowsShot,
            DistanceTraveledStat distanceTraveled
    ) implements ServerStats {}
    public record ParkourStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            DistanceTraveledStat distanceTraveled,
            int parkoursCompleted,
            int worldRecords,
            int parkoursBuilt
    ) implements ServerStats {}

    public record CreativeStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            int blocksBroken,
            int blocksPlaced,
            int itemsCrafted,
            int toolsBroken,
            int highestKillstreak,
            int playersKilled,
            int deaths,
            int arrowsShot,
            DistanceTraveledStat distanceTraveled
    ) implements ServerStats {}

    public record PrisonStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            int blocksBroken,
            int blocksPlaced,
            int itemsCrafted,
            int toolsBroken,
            int highestKillstreak,
            int playersKilled,
            int deaths,
            int arrowsShot,
            DistanceTraveledStat distanceTraveled
    ) implements ServerStats {}

    public interface ServerStats {}
}
