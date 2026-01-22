package net.dzultra.jfa.apidata;

import net.dzultra.jfa.requests.PlayerSearchRequest;
import net.dzultra.jfa.responses.PlayerSearchResponse;

import java.util.List;


public class PlayerSearch extends APIDataObject<PlayerSearchResponse> {
    private final PlayerSearchRequest playerSearchRequest;
    private final PlayerSearchResponse playerSearchResponse;
    public PlayerSearchResponse.PlayerSearchResult playerSearchResult;


    public PlayerSearch(String username) {
        PlayerSearchRequest request = new PlayerSearchRequest(username);
        PlayerSearchResponse response = request.getResponse();
        super(response);
        this.playerSearchRequest = request;
        this.playerSearchResponse = response;
        dataHandler();
    }

    public PlayerSearch(PlayerSearchRequest request) {
        PlayerSearchResponse response = request.getResponse();
        super(response);
        this.playerSearchRequest = request;
        this.playerSearchResponse = response;
        dataHandler();
    }


    @Override
    protected void dataHandler() {
        List<PlayerSearchResponse.PlayerSearchResult> playerSearchResults = playerSearchResponse.players();
        if (playerSearchResults == null) throw new IncompleteResponseException(this);

        if (!getRequestedUsername().equals(playerSearchResults.getFirst().username())) throw new UsernameDifferenceException(this);

        this.playerSearchResult = this.playerSearchResponse.players().getFirst();
    }

    public String getRequestedUsername() {
        return this.playerSearchRequest.getUsername();
    }
}
