package net.dzultra.jfa.apidata;

import net.dzultra.jfa.requests.PlayerSearchRequest;
import net.dzultra.jfa.responses.PlayerSearchResponse;

import java.util.List;


public class PlayerSearch extends APIDataObject<PlayerSearchResponse> {
    private final PlayerSearchRequest playerSearchRequest;
    private final PlayerSearchResponse playerSearchResponse;
    private PlayerSearchResponse.PlayerSearchResult playerSearchResult;

    private String uuid;
    private String username;
    private String headUrl;

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

        if (!getRequestedUsername().equals(playerSearchResults.getFirst().username())) throw new NoExactMatchException(this);

        this.playerSearchResult = this.playerSearchResponse.players().getFirst();
        this.uuid = this.getPlayerSearchResult().uuid();
        this.username = this.getPlayerSearchResult().username();
        this.headUrl = this.getPlayerSearchResult().headUrl();
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public String getRequestedUsername() {
        return this.playerSearchRequest.getUsername();
    }

    private PlayerSearchResponse.PlayerSearchResult getPlayerSearchResult() {
        return this.playerSearchResult;
    }
}
