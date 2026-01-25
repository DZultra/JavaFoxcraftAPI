package net.dzultra.jfa.apidata;

public class NoExactMatchException extends RuntimeException {
    public NoExactMatchException(PlayerSearch playerSearch) {
        super("No exact name match found in PlayerSearch for '" + playerSearch.getRequestedUsername() + "' in " + playerSearch.response.toString());
    }
}
