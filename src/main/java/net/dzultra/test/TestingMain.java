package net.dzultra.test;

import net.dzultra.jfa.requests.PlayerSkinRequest;
import net.dzultra.jfa.responses.PlayerSkinResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestingMain {
    public static void main(String[] args) {
//        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("sonic").getResponse();
//
//        System.out.println("Response: " + playerSearchResponse);
//        System.out.println("name: " + playerSearchResponse.players().getFirst().username());
//        System.out.println("UUID: " + playerSearchResponse.players().getFirst().uuid());
//
        PlayerSkinResponse playerSkinResponse = new PlayerSkinRequest("662d93f1-c0b8-4c80-b36a-6375cb6bd39a", true).getResponse();
        Path outputPath = Path.of("src/main/resources/skin.png");
        try {
            if (playerSkinResponse.png() == null) {
                return;
            }
            Files.write(outputPath, playerSkinResponse.png());
        } catch (IOException e) {
            System.out.println("Failed to write skin to file: " + e.getMessage());
        }
//
//        PlayerStatisticsResponse playerStatisticsResponse = new PlayerStatisticsRequest("sonicscremer", true).getResponse();
//        System.out.println(playerStatisticsResponse.player().serverGamemodes().get(0).statistics().getFirst());
    }
}
