package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.PlayerStatisticsResponse;

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
}
