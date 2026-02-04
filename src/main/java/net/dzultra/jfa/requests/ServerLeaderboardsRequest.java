package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.ServerLeaderboardsResponse;
import net.dzultra.jfa.types.Gamemode;
import net.dzultra.jfa.types.Period;
import net.dzultra.jfa.types.leaderboards.LeaderboardType;

public class ServerLeaderboardsRequest extends Request<String> {
    private static final String baseEndpoint = "/web/leaderboard?";
    private final Gamemode gamemode;
    private final Period period;

    public ServerLeaderboardsRequest(Gamemode gamemode, Period period) {
        super(baseEndpoint, null);
        this.gamemode = gamemode;
        this.period = period;
    }

    public ServerLeaderboardsRequest(LeaderboardType type, Period period) {
        super(baseEndpoint, null);
        this.gamemode = type.getGamemode();
        this.period = period;
    }

    public Gamemode getGamemode() {
        return gamemode;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return this.getBaseEndpoint() + "server=" + this.gamemode.getName() + "&period=" +  this.period.getName();
    }

    @Override
    public String getUrl() {
        return "https://www.mcfoxcraft.com/api" + getFullEndpoint();
    }

    @Override
    public ServerLeaderboardsResponse getResponse() {
        Gson gson = new Gson();
        String stringResponse = getStringResponse();

        if (!this.isValidResponse(stringResponse)) {
            return new ServerLeaderboardsResponse(null);
        }
        return gson.fromJson(stringResponse, ServerLeaderboardsResponse.class);
    }
}
