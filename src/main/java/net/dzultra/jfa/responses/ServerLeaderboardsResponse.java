package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ServerLeaderboardsResponse(@Nullable @SerializedName("boards") List<Leaderboard> leaderboards) implements Response {
    public record Leaderboard(
            @SerializedName("title") String name,
            @SerializedName("players") List<LeaderboardEntry> entry
    ) {}

    public record LeaderboardEntry(
            String username,
            @SerializedName("avatar") String headUrl,
            String value,
            String position
    ) {}
}
