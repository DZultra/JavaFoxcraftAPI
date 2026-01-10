package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ServerLeaderboardMetadataResponse(
        @Nullable @SerializedName("servers") List<List<String>> gamemodes,
        @Nullable List<List<String>> periods,
        @Nullable @SerializedName("initial") InitialSelection initialArgs
) implements Response {
    public record InitialSelection(
            @SerializedName("server") String gamemode,
            String period
    ) {}
}
