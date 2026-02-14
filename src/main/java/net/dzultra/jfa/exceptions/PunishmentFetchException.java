package net.dzultra.jfa.exceptions;

import org.jsoup.nodes.Element;

public class PunishmentFetchException extends RuntimeException {
    public PunishmentFetchException(Element row) {
        super("Required Field is null in row: " + row);
    }
}
