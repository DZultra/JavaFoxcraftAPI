package net.dzultra.jfa.apidata;

import net.dzultra.jfa.exceptions.InvalidPlayerStatsException;
import net.dzultra.jfa.exceptions.InvalidResponseException;
import net.dzultra.jfa.requests.PlayerStatisticsRequest;
import net.dzultra.jfa.responses.PlayerStatisticsResponse;
import net.dzultra.jfa.types.Gamemode;
import net.dzultra.jfa.types.statistics.*;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PlayerStatistics extends APIDataObject<PlayerStatisticsRequest, PlayerStatisticsResponse>{
    private PlayerStats playerStats;

    public PlayerStatistics(String uuid) {
        this(new PlayerStatisticsRequest(uuid));
    }

    public PlayerStatistics(String data, boolean asUsername) {
        this(new PlayerStatisticsRequest(data, asUsername));
    }

    private PlayerStatistics(PlayerStatisticsRequest request) {
        super(request, request.getResponse());
        dataHandler();
    }

    @Override
    protected void dataHandler() {
        PlayerStatisticsResponse.PlayerStatisticsResult pSResult = this.response.playerStatisticsResult();
        if (pSResult == null) throw new InvalidResponseException(this);

        String gamemodeText = pSResult.currentGamemode().getFirst();

        boolean isOnline = false;
        boolean isBedrock = false;
        Badge badge = null;

        if (!pSResult.status().isEmpty()) {
            isOnline = pSResult.status().getFirst().stream().anyMatch(Predicate.isEqual("Online"));
            isBedrock = pSResult.status().getFirst().stream().anyMatch(Predicate.isEqual("Bedrock"));

            for (String string : pSResult.status().getFirst()) {
                if (string.contains("badge--")) {
                    if (string.substring("badge--".length()).equals("green")) {
                        badge = Badge.GREEN;
                    } else if (string.substring("badge--".length()).equals("blue")) {
                        badge = Badge.BLUE;
                    }
                }
            }
        }

        boolean isHidden = gamemodeText.contains("server hidden");
        Gamemode gamemode = null;
        String lastSeen = null;
        if (isHidden) {
            gamemode = Gamemode.HIDDEN;
        } else if (gamemodeText.contains("Last seen:")) {
            gamemode = Gamemode.OFFLINE;
            lastSeen = gamemodeText;
        } else if (gamemodeText.contains("Online on ")) {
            gamemode = Gamemode.getGamemodeByName(gamemodeText.substring("Online on ".length()));
        }

        PlayerStats.OneblockStats oneblockStats = null;
        PlayerStats.SurvivalStats survivalStats = null;
        PlayerStats.SkyblockStats skyblockStats = null;
        PlayerStats.CreativeStats creativeStats = null;
        PlayerStats.KingdomsStats kingdomsStats = null;
        PlayerStats.ParkourStats parkourStats = null;
        PlayerStats.PrisonStats prisonStats = null;

        // For each gamemode the server returned for this player, process it.
        for (PlayerStatisticsResponse.GamemodeEntry entry : pSResult.serverGamemodes()) {
            // Based on the gamemode name, call the correct handler.
            switch (entry.name()) {
                case "Oneblock" -> oneblockStats = handleOneblockStats(entry);
                case "Survival" -> survivalStats = handleSurvivalStats(entry);
                case "Skyblock" -> skyblockStats = handleSkyblockStats(entry);
                case "Kingdoms" -> kingdomsStats = handleKingdomsStats(entry);
                case "Parkour" -> parkourStats = handleParkourStats(entry);
                case "Creative" -> creativeStats = handleCreativeStats(entry);
                case "Prison" -> prisonStats = handlePrisonStats(entry);
                default -> throw new InvalidPlayerStatsException(this, entry);
            }
        }

        if (oneblockStats == null
                || survivalStats == null
                || skyblockStats == null
                || creativeStats == null
                || kingdomsStats == null
                || parkourStats == null
                || prisonStats == null) {
            throw new InvalidPlayerStatsException(this, pSResult);
        }

        this.playerStats = new PlayerStats(
                pSResult.username(),
                pSResult.headUrl(),
                isOnline,
                isBedrock,
                isHidden,
                gamemode,
                badge,
                lastSeen,
                oneblockStats,
                survivalStats,
                skyblockStats,
                kingdomsStats,
                parkourStats,
                creativeStats,
                prisonStats
        );
    }

    @Override
    public String getName() {
        return "PlayerStatistics";
    }

    public PlayerStats getPlayerStats() {
        return this.playerStats;
    }

    private int intStat(List<List<String>> stats, int index) {
        String stat = stats.get(index).get(1);
        if (stat.matches("N/A")) return 0;
        return Integer.parseInt(stat);
    }

    private TimePlayedStat timePlayed(List<List<String>> stats) {
        return new TimePlayedStat(stats.get(0).get(1));
    }

    private DistanceTraveledStat distance(List<List<String>> stats) {
        return new DistanceTraveledStat(stats.get(9).get(1));
    }

    private <T extends PlayerStats.ServerStats> T handleStats(
            PlayerStatisticsResponse.GamemodeEntry entry,
            Function<List<List<String>>, T> constructor
    ) {
        try {
            return constructor.apply(entry.statistics());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new InvalidPlayerStatsException(this, entry);
        }
    }

    private PlayerStats.OneblockStats handleOneblockStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        return handleStats(entry, stats ->
                new PlayerStats.OneblockStats(
                        entry.name(),
                        new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                        timePlayed(stats),
                        intStat(stats, 1),
                        intStat(stats, 2),
                        intStat(stats, 3),
                        intStat(stats, 4),
                        intStat(stats, 5),
                        intStat(stats, 6),
                        intStat(stats, 7),
                        intStat(stats, 8),
                        distance(stats)
                )
        );
    }

    private PlayerStats.SurvivalStats handleSurvivalStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        return handleStats(entry, stats ->
                new PlayerStats.SurvivalStats(
                        entry.name(),
                        new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                        timePlayed(stats),
                        intStat(stats, 1),
                        intStat(stats, 2),
                        intStat(stats, 3),
                        intStat(stats, 4),
                        intStat(stats, 5),
                        intStat(stats, 6),
                        intStat(stats, 7),
                        intStat(stats, 8),
                        distance(stats)
                )
        );
    }

    private PlayerStats.SkyblockStats handleSkyblockStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        return handleStats(entry, stats ->
                new PlayerStats.SkyblockStats(
                        entry.name(),
                        new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                        timePlayed(stats),
                        intStat(stats, 1),
                        intStat(stats, 2),
                        intStat(stats, 3),
                        intStat(stats, 4),
                        intStat(stats, 5),
                        intStat(stats, 6),
                        intStat(stats, 7),
                        intStat(stats, 8),
                        distance(stats)
                )
        );
    }

    private PlayerStats.KingdomsStats handleKingdomsStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        return handleStats(entry, stats ->
                new PlayerStats.KingdomsStats(
                        entry.name(),
                        new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                        timePlayed(stats),
                        intStat(stats, 1),
                        intStat(stats, 2),
                        intStat(stats, 3),
                        intStat(stats, 4),
                        intStat(stats, 5),
                        intStat(stats, 6),
                        intStat(stats, 7),
                        intStat(stats, 8),
                        distance(stats)
                )
        );
    }

    // Woooo! I'm special - Shut up
    private PlayerStats.ParkourStats handleParkourStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        List<List<String>> stats = entry.statistics();
        PlayerStats.ParkourStats parkourStats;
        try {
            parkourStats = new PlayerStats.ParkourStats(
                    entry.name(), new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                    new TimePlayedStat(stats.get(0).get(1)),
                    new DistanceTraveledStat(stats.get(1).get(1)),
                    Integer.parseInt(stats.get(2).get(1)),
                    Integer.parseInt(stats.get(3).get(1)),
                    Integer.parseInt(stats.get(4).get(1)));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new InvalidPlayerStatsException(this, entry);
        }
        return parkourStats;
    }

    private PlayerStats.CreativeStats handleCreativeStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        return handleStats(entry, stats ->
                new PlayerStats.CreativeStats(
                        entry.name(),
                        new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                        timePlayed(stats),
                        intStat(stats, 1),
                        intStat(stats, 2),
                        intStat(stats, 3),
                        intStat(stats, 4),
                        intStat(stats, 5),
                        intStat(stats, 6),
                        intStat(stats, 7),
                        intStat(stats, 8),
                        distance(stats)
                )
        );
    }


    private PlayerStats.PrisonStats handlePrisonStats(PlayerStatisticsResponse.GamemodeEntry entry) {
        return handleStats(entry, stats ->
                new PlayerStats.PrisonStats(
                        entry.name(),
                        new InitialGamemodeJoinDateStat(entry.initialPlayerJoinDate()),
                        timePlayed(stats),
                        intStat(stats, 1),
                        intStat(stats, 2),
                        intStat(stats, 3),
                        intStat(stats, 4),
                        intStat(stats, 5),
                        intStat(stats, 6),
                        intStat(stats, 7),
                        intStat(stats, 8),
                        distance(stats)
                )
        );
    }
}
