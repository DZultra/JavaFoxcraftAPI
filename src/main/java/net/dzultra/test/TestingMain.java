package net.dzultra.test;

import net.dzultra.jfa.requests.PlayerStatisticsRequest;
import net.dzultra.jfa.responses.PlayerStatisticsResponse;

public class TestingMain {
    public static void main(String[] args) {
//        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("sonic").getResponse();
//
//        System.out.println("Response: " + playerSearchResponse);
//        System.out.println("name: " + playerSearchResponse.players().getFirst().username());
//        System.out.println("UUID: " + playerSearchResponse.players().getFirst().uuid());
//
//        PlayerSkinResponse playerSkinResponse = new PlayerSkinRequest("4af3a68e-0d3a-4e62-98fa-499535c31080").getResponse();
//        Path outputPath = Path.of("src/main/resources/skin.png");
//        try {
//            if (playerSkinResponse.png() == null) {
//                return;
//            }
//            Files.write(outputPath, playerSkinResponse.png());
//        } catch (IOException e) {
//            System.out.println("Failed to write skin to file: " + e.getMessage());
//        }

        PlayerStatisticsResponse playerStatisticsResponse = new PlayerStatisticsRequest("sonicscremer", true).getResponse();
        System.out.println(playerStatisticsResponse.player().serverGamemodes().get(0).statistics().getFirst());


    }
}
