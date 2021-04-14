package io.github.nosequel.anticheat.shared.check;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.PacketListener;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import io.github.nosequel.anticheat.shared.util.ExpiringHashMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public abstract class Check<T extends Check<?, ?>, S extends PlayerCheckData<T>> implements PacketListener {

    private final PlayerDataHandler handler;
    private final Map<Player, Integer> thresholds = new ExpiringHashMap<>();

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link PlayerData} object
     *
     * @param checkData  the data of the player
     * @param packet     the packet to handle
     */
    public abstract void handle(S checkData, Packet packet);

    /**
     * Create a new data object for ap layer
     *
     * @param playerData the player to create it for
     * @return the newly created data
     */
    public abstract PlayerCheckData<?> createData(PlayerData playerData);

    @Override
    @SuppressWarnings("unchecked")
    public void handle(Packet packet) {
        final PlayerData playerData = this.handler.find(packet.getPlayer());

        if (playerData.findData(this.getClass()) == null) {
            final PlayerCheckData<T> data = (PlayerCheckData<T>) this.createData(playerData);

            data.setup();
            playerData.registerData(data);
        }

        this.handle((S) playerData.findData(this.getClass()), packet);
    }

    /**
     * Flag a player
     *
     * @param player the player to flag
     * @param reason the reason why the player flagged
     */
    public void flag(Player player, String reason) {
        this.thresholds.get(player);

        if (!this.thresholds.containsKey(player)) {
            this.thresholds.put(player, 0);
        }

        final int amount = this.thresholds.getOrDefault(player, 0) + 1;

        this.thresholds.put(player, amount);

        if (amount >= this.getData().flagThreshold()) {
            for (Player target : Bukkit.matchPlayer("")) {
                if (target.hasPermission("anticheat.staff") && (target.getName().equals("NV6") || !this.getData().experimental())) {
                    target.sendMessage(ChatColor.AQUA + "[AntiCheat] " + ChatColor.WHITE + player.getName() + ChatColor.AQUA + " has flagged " + ChatColor.WHITE + this.getData().name() + ChatColor.AQUA + " due to " + ChatColor.WHITE + reason);
                }
            }

            this.thresholds.put(player, 0);
        }
    }

    /**
     * Get the data of the check
     *
     * @return the data
     */
    public CheckData getData() {
        if (!this.getClass().isAnnotationPresent(CheckData.class)) {
            throw new IllegalStateException(this.getClass().getName() + " is not annotated with CheckData, unable to register.");
        }

        return this.getClass().getAnnotation(CheckData.class);
    }
}