package net.dzultra.jfa.apidata;

public class IncompleteLeaderboardException extends RuntimeException {
    protected IncompleteLeaderboardException(APIDataObject<?, ?> apiDataObject, int size) {
        super("The leaderboard data for "
                + apiDataObject.getName()
                + " is incomplete and cannot be processed. Only "
                + size +  "Entries: " + apiDataObject.getRawResponse());
    }
}
