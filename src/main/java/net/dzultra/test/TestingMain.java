package net.dzultra.test;

import net.dzultra.jfa.apidata.PlayerSearch;
import net.dzultra.jfa.apidata.PlayerStatistics;
import net.dzultra.jfa.apidata.ServerLeaderboard;
import net.dzultra.jfa.types.Period;
import net.dzultra.jfa.types.leaderboards.OneblockLeaderboards;

public class TestingMain {
    public static void main(String[] args) {
//        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("DZultra").getRawResponse();
//
//        System.out.println("Response: " + playerSearchResponse);
//        System.out.println("name: " + playerSearchResponse.players().getFirst().username());
//        System.out.println("UUID: " + playerSearchResponse.players().getFirst().uuid());
//
//        PlayerStatisticsResponse playerStatisticsResponse = new PlayerStatisticsRequest("DZultra", true).getRawResponse();
//        System.out.println(playerStatisticsResponse.player().serverGamemodes().get(0).statistics().getFirst());
//
//        ServerLeaderboardMetadataResponse serverLeaderboardMetadataResponse =
//                new ServerLeaderboardMetadataRequest().getRawResponse();
//        System.out.println(serverLeaderboardMetadataResponse);
//
//        ServerLeaderboardsResponse serverLeaderboardsResponse = new ServerLeaderboardsRequest(Gamemode.ONEBLOCK, PeriodMap.ALL_TIME).getRawResponse();
//        System.out.println(serverLeaderboardsResponse.leaderboards().getFirst().entry().get(0).username());

//        PlayerSkin playerSkin = new PlayerSkin("DZultra", false, true);
//        playerSkin.writeToPath(Path.of("src/main/resources/skin.png"));

//        PlayerSearch playerSearch = new PlayerSearch("DZultra");
//        System.out.println(playerSearch.getUuid());

//        ServerLeaderboardMetadata metadata = new ServerLeaderboardMetadata();
//        System.out.println(metadata.getGamemode(Gamemode.ONEBLOCK));
//        System.out.println(metadata.getPeriod(Period.YEARLY).timeline());

//        ServerLeaderboard leaderboard = new ServerLeaderboard(ParkourLeaderboards.PARKOUR_SHARDS_GAINED, Period.DAILY);
//        System.out.println(leaderboard.getLeaderboardType() + " | " + leaderboard.getLeaderboardType().getGamemode());
//
//        System.out.println(leaderboard.getTitle());

        PlayerStatistics playerStatistics = new PlayerStatistics("DZultra", true);
        System.out.println(playerStatistics.getPlayerStats().lastSeen());
        PlayerSearch playerSearch = new PlayerSearch("DZultra");
        ServerLeaderboard serverLeaderboard = new ServerLeaderboard(OneblockLeaderboards.WARPS_VISITED, Period.DAILY);
        System.out.println(serverLeaderboard.getEntry(0));
    }
}
