package net.dzultra.jfa.requests;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.dzultra.jfa.responses.ErrorResponse;

import java.net.http.HttpClient;

public abstract class Request<T> {
    protected static final HttpClient httpClient = HttpClient.newHttpClient();
    public final String baseEndpoint;
    public final String data;

    protected Request(String baseEndpoint, String data) {
        this.baseEndpoint = baseEndpoint;
        this.data = data;
    }

    protected final HttpClient getHttpClient() {
        return httpClient;
    }

    public abstract String getBaseEndpoint();
    public abstract String getFullEndpoint();
    public abstract String getUrl();

    protected boolean isValidResponse(String stringResponse) {
        Gson gson = new Gson();
        try {
            ErrorResponse errorResponse = gson.fromJson(stringResponse, ErrorResponse.class);

            if (errorResponse == null) {
                return false;
            }

            if (errorResponse.response() != null) {
                System.out.println("Error: " + errorResponse.response());
                return false;
            } else {
                return true;
            }
        } catch (IllegalStateException | JsonSyntaxException e) {
            return false;
        }
    }

    protected boolean isValidUsername(String username) {
        if (username == null) return false;
        String MC_USERNAME_REGEX = "^[a-zA-Z0-9_]{3,16}$";
        return username.matches(MC_USERNAME_REGEX);
    }
}
