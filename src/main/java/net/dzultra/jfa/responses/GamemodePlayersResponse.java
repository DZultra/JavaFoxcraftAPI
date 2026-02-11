package net.dzultra.jfa.responses;

import net.dzultra.jfa.types.Gamemode;

public interface GamemodePlayersResponse extends Response {
    Gamemode getGamemode();

    interface PlayerEntry {
        String getWorld();
        String getName();
        String getUuid();
        Integer getX();
        Integer getY();
        Integer getZ();
        Integer getHealth();
        Integer getArmor();
        Integer getYaw();
    }
}
