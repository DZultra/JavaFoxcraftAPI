package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class InvalidPlayerStatsException extends RuntimeException {
    public InvalidPlayerStatsException(APIDataObject<?, ?> apiDataObject, Object problematicField) {
        super("PlayerStats data is invalid in " + apiDataObject.getName() + ". Problematic field: " + problematicField);
    }
}
