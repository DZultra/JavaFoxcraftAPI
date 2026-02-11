package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.KingdomsPlayersResponse;
import net.dzultra.jfa.types.GamemodePlayersType;

public class KingdomsPlayersRequest extends GamemodePlayersRequest {
    public KingdomsPlayersRequest() {
        super(GamemodePlayersType.KINGDOMS);
    }

    @Override
    public KingdomsPlayersResponse getResponse() {
        Gson gson = new Gson();
        String stringResponse = getStringResponse();

        if (stringResponse == null) {
            return new KingdomsPlayersResponse(null, null);
        }
        return gson.fromJson(stringResponse, KingdomsPlayersResponse.class);
    }
}
