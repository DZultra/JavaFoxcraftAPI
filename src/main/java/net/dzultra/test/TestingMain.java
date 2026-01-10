package net.dzultra.test;

import net.dzultra.jfa.requests.ServerLeaderboardsRequest;
import net.dzultra.jfa.requests.leaderboards.Gamemode;
import net.dzultra.jfa.requests.leaderboards.Period;
import net.dzultra.jfa.responses.ServerLeaderboardsResponse;

public class TestingMain {
    public static void main(String[] args) {
//        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("DZultra").getResponse();
//
//        System.out.println("Response: " + playerSearchResponse);
//        System.out.println("name: " + playerSearchResponse.players().getFirst().username());
//        System.out.println("UUID: " + playerSearchResponse.players().getFirst().uuid());

//        PlayerSkinResponse playerSkinResponse = new PlayerSkinRequest("662d93f1-c0b8-4c80-b36a-6375cb6bd39a", true).getResponse();
//        Path outputPath = Path.of("src/main/resources/skin.png");
//        try {
//            if (playerSkinResponse.png() == null) {
//                return;
//            }
//            Files.write(outputPath, playerSkinResponse.png());
//        } catch (IOException e) {
//            System.out.println("Failed to write skin to file: " + e.getMessage());
//        }

//        PlayerStatisticsResponse playerStatisticsResponse = new PlayerStatisticsRequest("DZultra", true).getResponse();
//        System.out.println(playerStatisticsResponse.player().serverGamemodes().get(0).statistics().getFirst());

//        ServerLeaderboardMetadataResponse serverLeaderboardMetadataResponse =
//                new ServerLeaderboardMetadataRequest().getResponse();
//        System.out.println(serverLeaderboardMetadataResponse);

        ServerLeaderboardsResponse serverLeaderboardsResponse = new ServerLeaderboardsRequest(Gamemode.ONEBLOCK, Period.ALL_TIME).getResponse();
        System.out.println(serverLeaderboardsResponse.leaderboards().getFirst().entry().get(0).username());
    }
}
