package net.dzultra.jfa.apidata;

import net.dzultra.jfa.responses.Response;

public abstract class APIDataObject<RESPONSE_TYPE extends Response> {
    protected final Response response;

    protected APIDataObject(Response response) {
        this.response = response;
    }

    protected abstract void dataHandler();
}
