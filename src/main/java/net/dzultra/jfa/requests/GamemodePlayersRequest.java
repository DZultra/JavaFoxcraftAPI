package net.dzultra.jfa.requests;

import net.dzultra.jfa.responses.GamemodePlayersResponse;
import net.dzultra.jfa.types.GamemodePlayersType;

public class GamemodePlayersRequest extends Request<String>{


    public GamemodePlayersRequest(GamemodePlayersType type) {
        super(type.getGamemode().getName(), null);
    }


    @Override
    public String getBaseEndpoint() {
        return this.baseEndpoint;
    }

    @Override
    public String getFullEndpoint() {
        return this.getBaseEndpoint();
    }

    @Override
    public String getUrl() {
        return "https://map.foxcraft.net/" + getFullEndpoint() + "/tiles/players.json";
    }

    @Override
    public GamemodePlayersResponse getResponse() {
        return null;
    }
}
