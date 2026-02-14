package net.dzultra.jfa.punishments;

import net.dzultra.jfa.apidata.PlayerSearch;
import net.dzultra.jfa.exceptions.PunishmentOutOfBoundsException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlayerPunishments {

    private final String uuid;
    private final List<Punishment> punishments;

    public PlayerPunishments(@NotNull String username) {
        this.uuid = new PlayerSearch(username).getUuid();
        this.punishments = LiteBansScraper.fetchPunishments(this.uuid);
    }

    public PlayerPunishments(@NotNull String data, boolean asUUID) {
        if (asUUID) {
            this.uuid = data;
            this.punishments = LiteBansScraper.fetchPunishments(this.uuid);
        } else {
            this.uuid = new PlayerSearch(data).getUuid();
            this.punishments = LiteBansScraper.fetchPunishments(this.uuid);
        }
    }

    @Nullable
    public String getUuid() {
        return uuid;
    }

    public List<Punishment> getPunishments() {
        return this.punishments;
    }

    public Punishment getPunishment(int index) {
        try {
            return this.punishments.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new PunishmentOutOfBoundsException(this.punishments, index);
        }
    }
}
