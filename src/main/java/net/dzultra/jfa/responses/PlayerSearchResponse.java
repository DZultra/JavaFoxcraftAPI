package net.dzultra.jfa.responses;

import java.util.List;

public record PlayerSearchResponse(List<PlayerSearchResult> results) {
    public record PlayerSearchResult(String uuid, String username, String avatar) {}
}
