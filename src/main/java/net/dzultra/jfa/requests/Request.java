package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.dzultra.jfa.responses.ErrorResponse;
import net.dzultra.jfa.responses.MojangAPIResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class Request<T> {
    protected static final HttpClient httpClient = HttpClient.newHttpClient();
    public final String baseEndpoint;
    public final String data;

    protected Request(String baseEndpoint, String data) {
        this.baseEndpoint = baseEndpoint;
        this.data = data;
    }

    protected final HttpClient getHttpClient() {
        return httpClient;
    }

    public abstract String getBaseEndpoint();
    public abstract String getFullEndpoint();
    public abstract String getUrl();

    protected boolean isValidResponse(String stringResponse) {
        Gson gson = new Gson();
        try {
            ErrorResponse errorResponse = gson.fromJson(stringResponse, ErrorResponse.class);

            if (errorResponse == null) {
                return false;
            }

            if (errorResponse.response() != null) {
                System.out.println("Error: " + errorResponse.response());
                return false;
            } else {
                return true;
            }
        } catch (IllegalStateException | JsonSyntaxException e) {
            return false;
        }
    }

    protected boolean isValidUsername(String username) {
        if (username == null) return false;
        String MC_USERNAME_REGEX = "^[a-zA-Z0-9_]{3,16}$";
        return username.matches(MC_USERNAME_REGEX);
    }

    protected String getUuidFromUsername(String username) {
        Gson gson = new Gson();
        if (!this.isValidUsername(username)) {
            return null;
        }

        String url = "https://api.mojang.com/users/profiles/minecraft/" + username;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = this.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body() == null) return null;

            MojangAPIResponse mojangAPIResponse = gson.fromJson(response.body(), MojangAPIResponse.class);

            return getNonTrimmedUUID(mojangAPIResponse.id());

        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.out.println("Error during PlayerSearchRequest HTTP call: " + e.getMessage());
            return null;
        }
    }

    private String getNonTrimmedUUID(String uuid) {
        return uuid.substring(0, 8) + "-" +
                uuid.substring(8, 12) + "-" +
                uuid.substring(12, 16) + "-" +
                uuid.substring(16, 20) + "-" +
                uuid.substring(20, 32);
    }
}
