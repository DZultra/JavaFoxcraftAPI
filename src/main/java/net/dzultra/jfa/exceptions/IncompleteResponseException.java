package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class IncompleteResponseException extends RuntimeException {
    public IncompleteResponseException(APIDataObject<?, ?> apiDataObject) {
        super("Incomplete Response in " + apiDataObject.getRawResponse() + ". Required part is null!");
    }
}
