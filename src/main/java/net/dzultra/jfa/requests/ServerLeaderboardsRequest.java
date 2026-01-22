package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.requests.types.Gamemode;
import net.dzultra.jfa.requests.types.Period;
import net.dzultra.jfa.responses.ServerLeaderboardsResponse;

public class ServerLeaderboardsRequest extends Request<String> {
    private static final String baseEndpoint = "/web/leaderboard?";
    private final Gamemode gamemode;
    private final Period period;

    public ServerLeaderboardsRequest(Gamemode gamemode, Period period) {
        super(baseEndpoint, null);
        this.gamemode = gamemode;
        this.period = period;
    }

    @Override
    public String getBaseEndpoint() {
        return baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return this.getBaseEndpoint() + "server=" + getGamemodeName() + "&period=" +  getPeriodName();
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

    private String getGamemodeName() {
        String gamemodeName = "";

        switch (this.gamemode) {
            case Gamemode.ONEBLOCK -> gamemodeName = "oneblock";
            case Gamemode.SURVIVAL -> gamemodeName = "survival";
            case Gamemode.KINGDOMS -> gamemodeName = "kingdoms";
            case Gamemode.PARKOUR -> gamemodeName = "parkour";
            default -> System.out.println("Invalid gamemode provided, somehow... Make sure you know what you're doing");
        }

        return gamemodeName;
    }

    private String getPeriodName() {
        String periodName = "";

        switch (this.period) {
            case Period.DAILY -> periodName = "daily";
            case Period.WEEKLY -> periodName = "weekly";
            case Period.MONTHLY -> periodName = "monthly";
            case Period.YEARLY -> periodName = "yearly";
            case Period.ALL_TIME -> periodName = "alltime";
            default -> System.out.println("Invalid period provided, somehow... Make sure you know what you're doing");
        }

        return periodName;
    }
}
