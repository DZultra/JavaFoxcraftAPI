package net.dzultra.jfa.apidata;

import net.dzultra.jfa.exceptions.InvalidResponseException;
import net.dzultra.jfa.requests.SurvivalPlayersRequest;
import net.dzultra.jfa.responses.SurvivalPlayersResponse;

import java.util.List;

public class SurvivalPlayers extends APIDataObject<SurvivalPlayersRequest, SurvivalPlayersResponse> {
    private List<SurvivalPlayersResponse.SurvivalPlayerEntry> playerEntries;
    private int max_players;

    public SurvivalPlayers() {
        this(new SurvivalPlayersRequest());
    }

    private SurvivalPlayers(SurvivalPlayersRequest request) {
        super(request, request.getResponse());
        dataHandler();
    }

    @Override
    protected void dataHandler() {
        List<SurvivalPlayersResponse.SurvivalPlayerEntry> playerEntries = this.response.playerEntries();
        Integer maxPlayers = this.response.max_players();

        if (maxPlayers == null || playerEntries == null) {
            throw new InvalidResponseException(this);
        }

        this.playerEntries = playerEntries;
        this.max_players = maxPlayers;
    }

    @Override
    public String getName() {
        return "SurvivalPlayers";
    }

    public List<SurvivalPlayersResponse.SurvivalPlayerEntry> getPlayerEntries() {
        return this.playerEntries;
    }

    public int getMaxPlayers() {
        return this.max_players;
    }

}
