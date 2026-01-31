package net.dzultra.jfa.apidata;

public class NoExactMatchException extends RuntimeException {
    protected NoExactMatchException(APIDataObject apiDataObject, String value) {
        super("No exact name match found in " + apiDataObject.getName() + " for '" + value + "'. Object: " + apiDataObject);
    }
}
