package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;
import net.dzultra.jfa.types.Gamemode;

import java.util.List;

public record SurvivalPlayersResponse(
        @SerializedName("max") Integer max_players,
        @SerializedName("players") List<SurvivalPlayerEntry> playerEntries
) implements GamemodePlayersResponse {
    @Override
    public Gamemode getGamemode() {
        return Gamemode.SURVIVAL;
    }

    public record SurvivalPlayerEntry (
            String world,
            int armor,
            String name,
            int x,
            int y,
            int health,
            int z,
            String uuid,
            int yaw
    ){}
}
