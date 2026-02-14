package net.dzultra.jfa.exceptions;

import java.io.IOException;

public class JsoupConnectionException extends RuntimeException {
    public JsoupConnectionException(IOException e, String url) {
        super("Jsoup Connection to " + url + " failed: " + e.getMessage());
    }
}
