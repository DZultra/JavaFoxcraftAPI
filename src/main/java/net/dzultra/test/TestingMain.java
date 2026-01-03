package net.dzultra.test;

import net.dzultra.jfa.requests.PlayerSearchRequest;
import net.dzultra.jfa.responses.PlayerSearchResponse;


public class TestingMain {
    public static void main(String[] args) {
        PlayerSearchResponse playerSearchResponse = new PlayerSearchRequest("a").getResponse();

        System.out.println("Response: " + playerSearchResponse);
    }
}
