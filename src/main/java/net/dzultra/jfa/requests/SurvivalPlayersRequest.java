package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import net.dzultra.jfa.responses.SurvivalPlayersResponse;
import net.dzultra.jfa.types.GamemodePlayersType;

public class SurvivalPlayersRequest extends GamemodePlayersRequest {
    public SurvivalPlayersRequest() {
        super(GamemodePlayersType.SURVIVAL);
    }

    @Override
    public SurvivalPlayersResponse getResponse() {
        Gson gson = new Gson();
        String stringResponse = getStringResponse();

        if (stringResponse == null) {
            return new SurvivalPlayersResponse(null, null);
        }
        return gson.fromJson(stringResponse, SurvivalPlayersResponse.class);
    }
}
