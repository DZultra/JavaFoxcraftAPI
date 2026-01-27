package net.dzultra.test;

import net.dzultra.jfa.apidata.PlayerSearch;
import net.dzultra.jfa.apidata.PlayerSkin;
import net.dzultra.jfa.apidata.ServerLeaderboardMetadata;
import net.dzultra.jfa.requests.types.Gamemode;
import net.dzultra.jfa.requests.types.Period;

import java.nio.file.Path;

public class TestingMain {
    public static void main(String[] args) {
//        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("DZultra").getResponse();
//
//        System.out.println("Response: " + playerSearchResponse);
//        System.out.println("name: " + playerSearchResponse.players().getFirst().username());
//        System.out.println("UUID: " + playerSearchResponse.players().getFirst().uuid());
//
//        PlayerStatisticsResponse playerStatisticsResponse = new PlayerStatisticsRequest("DZultra", true).getResponse();
//        System.out.println(playerStatisticsResponse.player().serverGamemodes().get(0).statistics().getFirst());
//
//        ServerLeaderboardMetadataResponse serverLeaderboardMetadataResponse =
//                new ServerLeaderboardMetadataRequest().getResponse();
//        System.out.println(serverLeaderboardMetadataResponse);
//
//        ServerLeaderboardsResponse serverLeaderboardsResponse = new ServerLeaderboardsRequest(Gamemode.ONEBLOCK, PeriodMap.ALL_TIME).getResponse();
//        System.out.println(serverLeaderboardsResponse.leaderboards().getFirst().entry().get(0).username());

        PlayerSkin playerSkin = new PlayerSkin("DZultra", false, true);
        playerSkin.writeToPath(Path.of("src/main/resources/skin.png"));

        PlayerSearch playerSearch = new PlayerSearch("Z_e_r_o_x_");
        System.out.println(playerSearch.getUuid());

        ServerLeaderboardMetadata metadata = new ServerLeaderboardMetadata();
        System.out.println(metadata.getGamemode(Gamemode.ONEBLOCK));
        System.out.println(metadata.getPeriod(Period.YEARLY).timeline());
    }
}
