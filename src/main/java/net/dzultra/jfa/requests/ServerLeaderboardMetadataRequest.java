package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.ServerLeaderboardMetadataResponse;

public class ServerLeaderboardMetadataRequest extends Request<String>{
    private static final String baseEndpoint = "/web/leaderboards";

    public ServerLeaderboardMetadataRequest() {
        super(baseEndpoint, null);
    }

    @Override
    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return this.getBaseEndpoint();
    }

    @Override
    public String getUrl() {
        return "https://www.mcfoxcraft.com/api" + getFullEndpoint();
    }

    @Override
    public ServerLeaderboardMetadataResponse getResponse() {
        Gson gson = new Gson();

        String responseString = getStringResponse();

        if (responseString == null) {
            return new ServerLeaderboardMetadataResponse(null, null, null);
        }

        return gson.fromJson(responseString, ServerLeaderboardMetadataResponse.class);
    }
}
