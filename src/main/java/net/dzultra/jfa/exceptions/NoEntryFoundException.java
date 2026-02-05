package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class NoEntryFoundException extends RuntimeException {
    public NoEntryFoundException(APIDataObject<?, ?> object, int position) {
        super("Did not find any entry at position: " + position + " for " + object.getRawResponse());
    }
}
