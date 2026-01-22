package net.dzultra.jfa.apidata;

public class IncompleteResponse extends RuntimeException {
    protected IncompleteResponse(APIDataObject apiDataObject) {
        super("Incomplete Response in " + apiDataObject.response + ". Required part is null!");
    }
}
