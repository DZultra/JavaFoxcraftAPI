package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.apidata.APIDataObject;

public class NoExactMatchException extends RuntimeException {
    public NoExactMatchException(APIDataObject<?, ?> apiDataObject, String value) {
        super("No exact name match found in " + apiDataObject.getName() + " for '" + value + "'. Object: " + apiDataObject);
    }
}
