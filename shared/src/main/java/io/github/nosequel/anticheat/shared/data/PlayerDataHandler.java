package io.github.nosequel.anticheat.shared.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataHandler {

    private final Map<UUID, PlayerData> playerData = new HashMap<>();

    /**
     * Find player data object by the player
     *
     * @param player the player to find it by
     * @return the data
     */
    public PlayerData find(Player player) {
        if (!this.playerData.containsKey(player.getUniqueId())) {
            this.playerData.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
        }

        return this.playerData.get(player.getUniqueId());
    }
}
