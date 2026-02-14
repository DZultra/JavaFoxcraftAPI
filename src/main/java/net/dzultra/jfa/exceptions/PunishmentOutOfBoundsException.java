package net.dzultra.jfa.exceptions;

import net.dzultra.jfa.punishments.Punishment;

import java.util.List;

public class PunishmentOutOfBoundsException extends RuntimeException {
    public PunishmentOutOfBoundsException(List<Punishment> punishments, int index) {
        super("Requested punishment with index " + index + " is out of bounds for Size: " + punishments.size());
    }
}
