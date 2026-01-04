package net.dzultra.jfa.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record PlayerSearchResponse(@SerializedName("results") List<PlayerSearchResult> players) implements Response{
    public record PlayerSearchResult(
            String uuid,
            String username,
            @SerializedName("avatar") String headUrl) {}
}
