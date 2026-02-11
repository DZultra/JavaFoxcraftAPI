package net.dzultra.jfa.apidata;

import net.dzultra.jfa.exceptions.InvalidResponseException;
import net.dzultra.jfa.requests.KingdomsPlayersRequest;
import net.dzultra.jfa.responses.KingdomsPlayersResponse;

import java.util.List;

public class KingdomsPlayers extends APIDataObject<KingdomsPlayersRequest, KingdomsPlayersResponse> {
    private List<KingdomsPlayersResponse.KingdomPlayerEntry> playerEntries;
    private int max_players;

    public KingdomsPlayers() {
        this(new KingdomsPlayersRequest());
    }

    private KingdomsPlayers(KingdomsPlayersRequest request) {
        super(request, request.getResponse());
        dataHandler();
    }

    @Override
    protected void dataHandler() {
        List<KingdomsPlayersResponse.KingdomPlayerEntry> playerEntries = this.response.playerEntries();
        Integer maxPlayers = this.response.max_players();

        if (maxPlayers == null || playerEntries == null) {
            throw new InvalidResponseException(this);
        }

        this.playerEntries = playerEntries;
        this.max_players = maxPlayers;
    }

    @Override
    public String getName() {
        return "KingdomsPlayers";
    }

    public List<KingdomsPlayersResponse.KingdomPlayerEntry> getPlayerEntries() {
        return this.playerEntries;
    }

    public int getMaxPlayers() {
        return this.max_players;
    }

}
