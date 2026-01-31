package net.dzultra.jfa.apidata;

public class IncompleteResponseException extends RuntimeException {
    protected IncompleteResponseException(APIDataObject<?, ?> apiDataObject) {
        super("Incomplete Response in " + apiDataObject.response + ". Required part is null!");
    }
}
