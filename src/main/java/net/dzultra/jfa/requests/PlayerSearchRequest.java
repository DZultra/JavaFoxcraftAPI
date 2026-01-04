package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.PlayerSearchResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Optional;

public class PlayerSearchRequest extends Request<String> {
    private final String username;

    private static final String baseEndpoint = "/web/search?query=";

    public PlayerSearchRequest(String username) {
        super(baseEndpoint, username);
        this.username = username;
    }

    @Override
    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return baseEndpoint + this.getUsername();
    }
    @Override
    public String getUrl() {
        return "https://www.mcfoxcraft.com/api" + getFullEndpoint();
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public PlayerSearchResponse getResponse() {
        Gson gson = new Gson();
        String stringResponse = getStringResponse();

        if (!this.isValidResponse(stringResponse)) {
            return new PlayerSearchResponse(new ArrayList<>());
        }
        return gson.fromJson(stringResponse, PlayerSearchResponse.class);
    }

    private String getStringResponse() {
        Optional<HttpResponse<String>> optionalResponse = this.getOptionalHttpResponse();
        // Return null if the response is not present
        return optionalResponse.map(HttpResponse::body).orElse(null);
    }

    private Optional<HttpResponse<String>> getOptionalHttpResponse() {
        try {
            System.out.println("Sending PlayerSearchRequest to URL: " + this.getUrl());
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.getUrl()))
                .GET()
                .build();
            return Optional.of(this.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.println("Error during PlayerSearchRequest HTTP call: " + e.getMessage());
            return Optional.empty();
        }
    }
}
