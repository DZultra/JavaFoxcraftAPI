package net.dzultra.jfa.apidata;

import net.dzultra.jfa.requests.Request;
import net.dzultra.jfa.responses.Response;

public abstract class APIDataObject<REQUEST_TYPE extends Request<?>, RESPONSE_TYPE extends Response> {
    protected REQUEST_TYPE request;
    protected RESPONSE_TYPE response;

    protected APIDataObject(REQUEST_TYPE request,RESPONSE_TYPE response) {
        this.request = request;
        this.response = response;
    }

    protected abstract void dataHandler();
}
