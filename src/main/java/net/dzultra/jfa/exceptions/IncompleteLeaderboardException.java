package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class IncompleteLeaderboardException extends RuntimeException {
    public IncompleteLeaderboardException(APIDataObject<?, ?> apiDataObject, int size) {
        super("The leaderboard data for "
                + apiDataObject.getName()
                + " is incomplete and cannot be processed. Only "
                + size +  "Entries: " + apiDataObject.getRawResponse());
    }
}
