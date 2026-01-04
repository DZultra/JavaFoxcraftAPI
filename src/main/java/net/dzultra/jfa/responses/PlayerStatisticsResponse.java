package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record PlayerStatisticsResponse(PlayerStatisticsResult player) implements Response {
    public record PlayerStatisticsResult(
            String username,
            @SerializedName("avatar") String headUrl,
            @SerializedName("tags") List<List<String>> status,
            @SerializedName("texts") List<String> playerGamemode,
            @SerializedName("cards") List<Gamemodes> serverGamemodes
    ) {}

    public record Gamemodes(
            @SerializedName("title") String name,
            @SerializedName("subtitle") String initialPlayerJoinDate,
            @SerializedName("cardItems") List<List<String>> statistics
    ) {}
}
