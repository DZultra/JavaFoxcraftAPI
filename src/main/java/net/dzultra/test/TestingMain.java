package net.dzultra.test;

import net.dzultra.jfa.requests.PlayerSkinRequest;
import net.dzultra.jfa.responses.PlayerSkinResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class TestingMain {
    public static void main(String[] args) {
//        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("a").getResponse();
//
//        System.out.println("Response: " + playerSearchResponse);

        PlayerSkinResponse playerSkinResponse = new PlayerSkinRequest("4af3a68e-0d3a-4e62-98fa-499535c31080").getResponse();
        Path outputPath = Path.of("src/main/resources/skin.png");
        try {
            if (playerSkinResponse.png() == null) {
                return;
            }
            Files.write(outputPath, playerSkinResponse.png());
        } catch (IOException e) {
            System.out.println("Failed to write skin to file: " + e.getMessage());
        }

    }
}
