package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.PlayerSearchResponse;

import java.util.ArrayList;

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
        return this.getBaseEndpoint() + this.getUsername();
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
}
