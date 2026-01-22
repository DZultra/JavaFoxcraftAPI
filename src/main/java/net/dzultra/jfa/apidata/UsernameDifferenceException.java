package net.dzultra.jfa.apidata;

public class UsernameDifferenceException extends RuntimeException {
    public UsernameDifferenceException(PlayerSearch playerSearch) {
        super("No exact name match found in PlayerSearch for '" + playerSearch.getRequestedUsername() + "' in " + playerSearch.response.toString());
    }
}
