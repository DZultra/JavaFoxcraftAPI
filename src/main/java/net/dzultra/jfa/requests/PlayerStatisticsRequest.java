package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.PlayerStatisticsResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class PlayerStatisticsRequest extends Request<String>{
    private final String uuid;
    private final String username;

    private static final String baseEndpoint = "/web/player?uuid=";

    public PlayerStatisticsRequest(String uuid) {
        super(baseEndpoint, uuid);

        if (!data.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
            System.out.println("Invalid UUID format provided.");
            this.uuid = "";
        } else {
            this.uuid = uuid;
        }
        this.username = null;
    }

    public PlayerStatisticsRequest(String data, boolean asUsername) {
        super(baseEndpoint, data);
        if (!asUsername) { // If asUsername is false, data is treated as UUID
            this.username = null;
            if (!data.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                System.out.println("Invalid UUID format provided.");
                this.uuid = "";
            } else {
                this.uuid = data;
            }
        } else { // If asUsername is true, data is treated as username
            this.username = data;
            this.uuid = getUuidFromUsername(username);
        }
    }

    @Override
    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return this.getBaseEndpoint() + this.getUuid();
    }

    @Override
    public String getUrl() {
        return "https://www.mcfoxcraft.com/api" + getFullEndpoint();
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override
    public PlayerStatisticsResponse getResponse() {
        Gson gson = new Gson();
        String stringResponse = getStringResponse();

        if (!this.isValidResponse(stringResponse)) {
            return new PlayerStatisticsResponse(null);
        }
        return gson.fromJson(stringResponse, PlayerStatisticsResponse.class);
    }

    private String getStringResponse() {
        Optional<HttpResponse<String>> optionalResponse = this.getOptionalHttpResponse();
        // Return null if the response is not present
        return optionalResponse.map(HttpResponse::body).orElse(null);
    }

    private Optional<HttpResponse<String>> getOptionalHttpResponse() {
        try {
            System.out.println("Sending PlayerStatisticsRequest to URL: " + this.getUrl());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.getUrl()))
                    .GET()
                    .build();
            return Optional.of(this.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.println("Error during PlayerStatisticsRequest HTTP call: " + e.getMessage());
            return Optional.empty();
        }
    }
}
