package net.dzultra.jfa.apidata;

import net.dzultra.jfa.requests.PlayerSearchRequest;
import net.dzultra.jfa.responses.PlayerSearchResponse;

import java.util.List;


public class PlayerSearch extends APIDataObject<PlayerSearchRequest,PlayerSearchResponse> {
    private PlayerSearchResponse.PlayerSearchResult playerSearchResult;

    private String uuid;
    private String username;
    private String headUrl;

    public PlayerSearch(String username) {
        PlayerSearchRequest request = new PlayerSearchRequest(username);
        PlayerSearchResponse response = request.getResponse();
        super(request, response);
        dataHandler();
    }

    public PlayerSearch(PlayerSearchRequest request) {
        PlayerSearchResponse response = request.getResponse();
        super(request, response);
        dataHandler();
    }


    @Override
    protected void dataHandler() {
        List<PlayerSearchResponse.PlayerSearchResult> playerSearchResults = response.players();
        if (playerSearchResults == null) throw new IncompleteResponseException(this);

        if (!getRequestedUsername().equals(playerSearchResults.getFirst().username())) throw new NoExactMatchException(this, username);

        this.playerSearchResult = this.response.players().getFirst();
        this.uuid = this.getPlayerSearchResult().uuid();
        this.username = this.getPlayerSearchResult().username();
        this.headUrl = this.getPlayerSearchResult().headUrl();
    }

    @Override
    public String getName() {
        return "PlayerSearch";
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
        return this.request.getUsername();
    }

    private PlayerSearchResponse.PlayerSearchResult getPlayerSearchResult() {
        return this.playerSearchResult;
    }
}
