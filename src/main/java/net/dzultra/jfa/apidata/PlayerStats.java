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
    ) implements GenericStat {
        @Override
        public String getTitle() { return this.title(); }

        @Override
        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        @Override
        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        @Override
        public int getBlocksBroken() { return this.blocksBroken(); }

        @Override
        public int getBlocksPlaced() { return this.blocksPlaced(); }

        @Override
        public int getItemsCrafted() {return this.itemsCrafted(); }

        @Override
        public int getToolsBroken() { return this.toolsBroken(); }

        @Override
        public int getHighestKillstreak() { return this.highestKillstreak(); }

        @Override
        public int getPlayersKilled() { return this.playersKilled(); }

        @Override
        public int getDeaths() { return this.deaths(); }

        @Override
        public int getArrowsShot() { return this.arrowsShot(); }

        @Override
        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }
    }

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
    ) implements GenericStat {
        @Override
        public String getTitle() { return this.title(); }

        @Override
        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        @Override
        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        @Override
        public int getBlocksBroken() { return this.blocksBroken(); }

        @Override
        public int getBlocksPlaced() { return this.blocksPlaced(); }

        @Override
        public int getItemsCrafted() {return this.itemsCrafted(); }

        @Override
        public int getToolsBroken() { return this.toolsBroken(); }

        @Override
        public int getHighestKillstreak() { return this.highestKillstreak(); }

        @Override
        public int getPlayersKilled() { return this.playersKilled(); }

        @Override
        public int getDeaths() { return this.deaths(); }

        @Override
        public int getArrowsShot() { return this.arrowsShot(); }

        @Override
        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }
    }

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
    ) implements GenericStat {
        @Override
        public String getTitle() { return this.title(); }

        @Override
        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        @Override
        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        @Override
        public int getBlocksBroken() { return this.blocksBroken(); }

        @Override
        public int getBlocksPlaced() { return this.blocksPlaced(); }

        @Override
        public int getItemsCrafted() {return this.itemsCrafted(); }

        @Override
        public int getToolsBroken() { return this.toolsBroken(); }

        @Override
        public int getHighestKillstreak() { return this.highestKillstreak(); }

        @Override
        public int getPlayersKilled() { return this.playersKilled(); }

        @Override
        public int getDeaths() { return this.deaths(); }

        @Override
        public int getArrowsShot() { return this.arrowsShot(); }

        @Override
        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }
    }

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
    ) implements GenericStat {
        @Override
        public String getTitle() { return this.title(); }

        @Override
        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        @Override
        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        @Override
        public int getBlocksBroken() { return this.blocksBroken(); }

        @Override
        public int getBlocksPlaced() { return this.blocksPlaced(); }

        @Override
        public int getItemsCrafted() {return this.itemsCrafted(); }

        @Override
        public int getToolsBroken() { return this.toolsBroken(); }

        @Override
        public int getHighestKillstreak() { return this.highestKillstreak(); }

        @Override
        public int getPlayersKilled() { return this.playersKilled(); }

        @Override
        public int getDeaths() { return this.deaths(); }

        @Override
        public int getArrowsShot() { return this.arrowsShot(); }

        @Override
        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }
    }

    public record ParkourStats(
            String title, InitialGamemodeJoinDateStat initialJoinDateStat,
            TimePlayedStat timePlayed,
            DistanceTraveledStat distanceTraveled,
            int parkoursCompleted,
            int worldRecords,
            int parkoursBuilt
    ) {
        public String getTitle() { return this.title(); }

        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }

        public int getParkoursCompleted() { return this.parkoursCompleted(); }

        public int getWorldRecords() { return this.worldRecords(); }

        public int getParkoursBuilt() { return this.parkoursBuilt(); }
    }

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
    ) implements GenericStat {
        @Override
        public String getTitle() { return this.title(); }

        @Override
        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        @Override
        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        @Override
        public int getBlocksBroken() { return this.blocksBroken(); }

        @Override
        public int getBlocksPlaced() { return this.blocksPlaced(); }

        @Override
        public int getItemsCrafted() {return this.itemsCrafted(); }

        @Override
        public int getToolsBroken() { return this.toolsBroken(); }

        @Override
        public int getHighestKillstreak() { return this.highestKillstreak(); }

        @Override
        public int getPlayersKilled() { return this.playersKilled(); }

        @Override
        public int getDeaths() { return this.deaths(); }

        @Override
        public int getArrowsShot() { return this.arrowsShot(); }

        @Override
        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }
    }

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
    ) implements GenericStat {
        @Override
        public String getTitle() { return this.title(); }

        @Override
        public InitialGamemodeJoinDateStat getInitialGamemodeJoinDate() { return this.initialJoinDateStat(); }

        @Override
        public TimePlayedStat getTimePlayed() { return this.timePlayed(); }

        @Override
        public int getBlocksBroken() { return this.blocksBroken(); }

        @Override
        public int getBlocksPlaced() { return this.blocksPlaced(); }

        @Override
        public int getItemsCrafted() {return this.itemsCrafted(); }

        @Override
        public int getToolsBroken() { return this.toolsBroken(); }

        @Override
        public int getHighestKillstreak() { return this.highestKillstreak(); }

        @Override
        public int getPlayersKilled() { return this.playersKilled(); }

        @Override
        public int getDeaths() { return this.deaths(); }

        @Override
        public int getArrowsShot() { return this.arrowsShot(); }

        @Override
        public DistanceTraveledStat getDistanceTraveled() { return this.distanceTraveled(); }
    }

    public interface GenericStat {
        String getTitle();
        InitialGamemodeJoinDateStat getInitialGamemodeJoinDate();
        TimePlayedStat getTimePlayed();
        int getBlocksBroken();
        int getBlocksPlaced();
        int getItemsCrafted();
        int getToolsBroken();
        int getHighestKillstreak();
        int getPlayersKilled();
        int getDeaths();
        int getArrowsShot();
        DistanceTraveledStat getDistanceTraveled();
    }
}
