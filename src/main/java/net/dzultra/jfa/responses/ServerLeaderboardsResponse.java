package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ServerLeaderboardsResponse(@Nullable @SerializedName("boards") List<LeaderboardResults> leaderboardResults) implements Response {
    public record LeaderboardResults(
            @SerializedName("title") String name,
            @SerializedName("playerEntries") List<LeaderboardEntry> entries
    ) {}

    public record LeaderboardEntry(
            String username,
            @SerializedName("avatar") String headUrl,
            String value,
            String position
    ) {}
}
