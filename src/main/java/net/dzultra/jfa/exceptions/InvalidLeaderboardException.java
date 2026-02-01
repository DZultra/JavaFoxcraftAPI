package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class InvalidLeaderboardException extends RuntimeException {
    public InvalidLeaderboardException(APIDataObject<?, ?> apiDataObject, int size) {
        super("The leaderboard data for "
                + apiDataObject.getName()
                + " is invalid and cannot be processed. Only "
                + size +  "Entries: " + apiDataObject.getRawResponse());
    }
}
