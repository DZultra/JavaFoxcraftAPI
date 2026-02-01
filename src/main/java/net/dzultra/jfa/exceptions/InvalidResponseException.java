package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class InvalidResponseException extends RuntimeException {
    public InvalidResponseException(APIDataObject<?, ?> apiDataObject) {
        super("Invalid Response in " + apiDataObject.getRawResponse() + ". Required part is null!");
    }
}
