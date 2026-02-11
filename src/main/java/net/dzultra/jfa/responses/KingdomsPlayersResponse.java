package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;
import net.dzultra.jfa.types.Gamemode;

import java.util.List;

public record KingdomsPlayersResponse(
        @SerializedName("max") Integer max_players,
        @SerializedName("players") List<KingdomPlayerEntry> playerEntries
) implements GamemodePlayersResponse {
    @Override
    public Gamemode getGamemode() {
        return Gamemode.KINGDOMS;
    }

    public record KingdomPlayerEntry (
            String world,
            String name,
            String uuid
    ) {}
}
