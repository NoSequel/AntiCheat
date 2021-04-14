package io.github.nosequel.anticheat.protocol.listener;

import io.github.nosequel.anticheat.protocol.PacketHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final PacketHandler handler;

    /**
     * Constructor to make a new player listener object
     *
     * @param handler the handler
     */
    public PlayerListener(PacketHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.handler.setupPacketListening(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.handler.cancelPacketListening(event.getPlayer());
    }

}
