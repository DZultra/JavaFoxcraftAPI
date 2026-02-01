package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.requests.Request;

public class InvalidUUIDException extends RuntimeException {
    public InvalidUUIDException(Request<?> request) {
        super("The provided UUID is not in the correct format for "
                + request
                + ".\nExpected format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx (8-4-4-4-12 hexadecimal characters). Provided UUID: "
                + request.data);
    }
}
