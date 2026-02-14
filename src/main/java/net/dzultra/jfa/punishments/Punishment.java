package net.dzultra.jfa.punishments;

public record Punishment(
        String type,
        String punishedPlayer,
        String punishedUuid,
        String moderator,
        String moderatorUuid,
        String reason,
        String date,
        String expires,
        String originServer
) {}
