package net.dzultra.jfa.apidata;

import net.dzultra.jfa.exceptions.InvalidLeaderboardException;
import net.dzultra.jfa.exceptions.InvalidResponseException;
import net.dzultra.jfa.exceptions.NoExactMatchException;
import net.dzultra.jfa.requests.ServerLeaderboardsRequest;
import net.dzultra.jfa.responses.ServerLeaderboardsResponse;
import net.dzultra.jfa.types.Gamemode;
import net.dzultra.jfa.types.leaderboards.LeaderboardType;
import net.dzultra.jfa.types.Period;

import java.util.List;

public class ServerLeaderboard extends APIDataObject<ServerLeaderboardsRequest,ServerLeaderboardsResponse>{
    private final LeaderboardType leaderboardType;
    private final Period period;
    private Leaderboard leaderboard;

    public ServerLeaderboard(LeaderboardType leaderboardType, Period period) {
        ServerLeaderboardsRequest request = new ServerLeaderboardsRequest(leaderboardType.getGamemode(), period);
        ServerLeaderboardsResponse response = request.getResponse();
        super(request, response);
        this.leaderboardType = leaderboardType;
        this.period = period;
        dataHandler();
    }

    @Override
    protected void dataHandler() {
        String title = this.leaderboardType.getTitle();
        int leaderboardId;
        List<ServerLeaderboardsResponse.LeaderboardResults> lbResults = this.response.leaderboardResults();


        if (lbResults == null || lbResults.isEmpty()) {
            throw new InvalidResponseException(this);
        }

        Gamemode gamemode = leaderboardType.getGamemode();
        var map = LeaderBoardIdMap.LEADERBOARD_IDS.get(gamemode);

        if (map == null || !map.containsKey(title)) throw new IllegalStateException("Unexpected value: " + this.leaderboardType.getGamemode() + ".\nThis error should kinda never be thrown. Wth are you doing?");

        leaderboardId = map.get(title);

        if (!lbResults.get(leaderboardId).name().equals(title)) throw new NoExactMatchException(this, title);
        if (lbResults.get(leaderboardId).entries().size() != 10)
            throw new InvalidLeaderboardException(this, lbResults.get(leaderboardId).entries().size());

        this.leaderboard = new Leaderboard(title, lbResults.get(leaderboardId).entries());
    }

    @Override
    public String getName() {
        return "ServerLeaderboard";
    }

    public LeaderboardType getLeaderboardType() {
        return leaderboardType;
    }

    public Period getPeriod() {
        return period;
    }

    public List<ServerLeaderboardsResponse.LeaderboardEntry> getEntries() {
        return leaderboard.entries();
    }

    public String getTitle() {
        return leaderboard.title();
    }

    public ServerLeaderboardsResponse.LeaderboardEntry getEntry(int position) {
        return leaderboard.entries().get(position - 1);
    }

    public record Leaderboard(String title, List<ServerLeaderboardsResponse.LeaderboardEntry> entries) {}
}
