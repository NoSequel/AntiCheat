package io.github.nosequel.anticheat.shared.check;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.PacketListener;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public abstract class Check implements PacketListener {

    private final PlayerDataHandler handler;

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link PlayerData} object
     *
     * @param playerData the player data
     * @param packet     the packet to handle
     */
    public abstract void handle(PlayerData playerData, Packet packet);

    @Override
    public void handle(Packet packet) {
        this.handle(this.handler.find(packet.getPlayer()), packet);
    }

    /**
     * Flag a player
     *
     * @param player the player to flag
     * @param reason the reason why the player flagged
     */
    public void flag(Player player, String reason) {
        for (Player target : Bukkit.matchPlayer("")) {
            if (target.hasPermission("anticheat.staff")) {
                target.sendMessage(ChatColor.AQUA + "[AntiCheat] " + ChatColor.WHITE + player.getName() + ChatColor.AQUA + " has flagged " + ChatColor.WHITE + this.getData().name() + ChatColor.AQUA + " due to " + ChatColor.WHITE + reason);
            }
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