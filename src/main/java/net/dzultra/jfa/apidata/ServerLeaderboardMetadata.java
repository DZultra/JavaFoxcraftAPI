package net.dzultra.jfa.apidata;

import net.dzultra.jfa.requests.ServerLeaderboardMetadataRequest;
import net.dzultra.jfa.types.Gamemode;
import net.dzultra.jfa.types.Period;
import net.dzultra.jfa.responses.ServerLeaderboardMetadataResponse;

import java.util.List;
import java.util.Optional;

public class ServerLeaderboardMetadata extends APIDataObject<ServerLeaderboardMetadataRequest,ServerLeaderboardMetadataResponse>{
    private List<GameModeMap> gamemodes = null;
    private List<PeriodMap> periods = null;


    public ServerLeaderboardMetadata() {
        ServerLeaderboardMetadataRequest request = new ServerLeaderboardMetadataRequest();
        ServerLeaderboardMetadataResponse response = request.getResponse();
        super(request, response);
        dataHandler();
    }

    @Override
    protected void dataHandler() {
        ServerLeaderboardMetadataResponse rawResponse = response;
        if (rawResponse.gamemodes() == null
                || rawResponse.periods() == null
                || rawResponse.initialArgs() == null
        ) throw new IncompleteResponseException(this);

        this.gamemodes = rawResponse.gamemodes().stream()
                .map(list -> new GameModeMap(list.get(0), list.get(1)))
                .toList();

        this.periods = rawResponse.periods().stream()
                .map(list -> new PeriodMap(list.get(0), list.get(1)))
                .toList();
    }

    public List<PeriodMap> getPeriods() {
        return periods;
    }

    public List<GameModeMap> getGamemodes() {
        return gamemodes;
    }

    public ServerLeaderboardMetadataResponse.InitialSelection getInitialSelection() {
        return response.initialArgs();
    }

    public GameModeMap getGamemode(Gamemode gamemodeType) {
        GameModeMap gameModeMap = gamemodes.stream()
                .filter(gamemode -> gamemode.serverId().equals(gamemodeType.getName()))
                .findFirst()
                .orElse(null);

        // If gameModeMap is null, throw exception, else return value
        return Optional.ofNullable(gameModeMap).orElseThrow(() -> new IncompleteResponseException(this));
    }

    public PeriodMap getPeriod(Period periodType) {
        PeriodMap periodMap = periods.stream()
                .filter(period -> period.period.equals(periodType.getName()))
                .findFirst()
                .orElse(null);

        // If periodMap is null, throw exception, else return value
        return Optional.ofNullable(periodMap).orElseThrow(() -> new IncompleteResponseException(this));
    }

    // Can't be null since dataHandler checks for it
    public String initialServer() {
        return response.initialArgs().gamemode();
    }

    public record GameModeMap(String serverId, String serverName) {}
    public record PeriodMap(String period, String timeline) {}
}

