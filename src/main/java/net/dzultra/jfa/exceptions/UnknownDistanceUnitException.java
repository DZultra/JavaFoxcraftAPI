package net.dzultra.jfa.exceptions;

public class UnknownDistanceUnitException extends RuntimeException {
    public UnknownDistanceUnitException(String givenUnit) {
        super("The distance unit given by the API is unknown: " + givenUnit);
    }
}
