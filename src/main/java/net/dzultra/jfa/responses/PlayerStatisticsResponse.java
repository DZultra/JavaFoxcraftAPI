package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public record PlayerStatisticsResponse(@Nullable @SerializedName("player") PlayerStatisticsResult playerStatisticsResult) implements Response {
    public record PlayerStatisticsResult(
            String username,
            @SerializedName("avatar") String headUrl,
            @SerializedName("tags") List<List<String>> status,
            @SerializedName("texts") List<String> currentGamemode,
            @SerializedName("cards") List<GamemodeEntry> serverGamemodes
    ) {}

    public record GamemodeEntry(
            @SerializedName("title") String name,
            @SerializedName("subtitle") String initialPlayerJoinDate,
            @SerializedName("cardItems") List<List<String>> statistics
    ) {}
}
