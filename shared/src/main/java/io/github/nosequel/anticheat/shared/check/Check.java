package io.github.nosequel.anticheat.shared.check;

import io.github.nosequel.anticheat.protocol.PacketListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Check implements PacketListener {

    /**
     * Flag a player
     *
     * @param player the player to flag
     * @param reason the reason why the player flagged
     */
    public void flag(Player player, String reason) {
        for (Player target : Bukkit.matchPlayer("")) {
            if (target.hasPermission("anticheat.staff")) {
                target.sendMessage(ChatColor.GOLD + "[" + this.getData().name() + "] " + ChatColor.YELLOW + player.getName() + ChatColor.WHITE + " has flagged due to " + reason);
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