package io.github.nosequel.anticheat.protocol;

import io.github.nosequel.anticheat.protocol.listener.PlayerListener;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import io.github.nosequel.anticheat.protocol.packets.PlayInPacketAnimation;
import io.github.nosequel.anticheat.protocol.packets.use.PlayInUseEntityPacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class PacketHandler {

    private final List<PacketListener> listeners = new ArrayList<>();

    /**
     * Constructor to make a new packet handler.
     * <p>
     * This method automatically registers the
     * required listeners and other stuff required for
     * the packet handler.
     *
     * @param plugin the plugin to register the stuff to.
     */
    public PacketHandler(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), plugin);
    }

    /**
     * Handle a packet for all registered listeners.
     * <p>
     * The listener must be inside of the listeners list in this class.
     *
     * @param packet the packet to handle
     */
    public void handle(Packet packet) {
        for (PacketListener listener : this.listeners) {
            listener.handle(packet);
        }
    }

    /**
     * Register a listener to the list of listeners.
     *
     * @param listener the listener to register
     */
    public void register(PacketListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Setup the packet listening for a player.
     * <p>
     * Packet listening must be setup for the player
     * so we can listen to the player's checks.
     *
     * @param player the player to register it for
     */
    public abstract void setupPacketListening(Player player);

    /**
     * Disable packet listening for a player.
     * <p>
     * This should be called whenever the player disconnects
     * from the server, so the handler doesn't have to listen
     * to it's packets anymore.
     *
     * @param player the player to cancel it for
     */
    public abstract void cancelPacketListening(Player player);

    /**
     * Convert an object to a {@link PlayInFlyingPacket}.
     *
     * @param player the player the packet is for
     * @param object the object to convert
     * @param <T>    the type of the object
     * @return the play in flying packet
     * @throws WrongTypeException thrown if the provided {@link T} object is of a wrong type
     */
    public abstract <T> PlayInFlyingPacket toFlyingPacket(Player player, T object) throws WrongTypeException;

    /**
     * Convert an object to a {@link PlayInUseEntityPacket}.
     *
     * @param player the player the packet is for
     * @param object the object to convert
     * @param <T>    the type of the object
     * @return the play in flying packet
     * @throws WrongTypeException thrown if the provided {@link T} object is of a wrong type
     */
    public abstract <T> PlayInUseEntityPacket toUsePacket(Player player, T object) throws WrongTypeException;

    /**
     * Convert an object to a {@link PlayInPacketAnimation}.
     *
     * @param player the player the packet is for
     * @param object the object to convert
     * @param <T>    the type of the object
     * @return the play in flying packet
     * @throws WrongTypeException thrown if the provided {@link T} object is of a wrong type
     */
    public abstract <T> PlayInPacketAnimation toAnimationPacket(Player player, T object) throws WrongTypeException;


}