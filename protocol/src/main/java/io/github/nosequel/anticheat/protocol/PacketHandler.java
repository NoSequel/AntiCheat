package io.github.nosequel.anticheat.protocol;

import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PacketHandler {

    private final List<PacketListener> listeners = new ArrayList<>();

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
     * Convert an object to a {@link PlayInFlyingPacket}.
     *
     * @param player the player the packet is for
     * @param object the object to convert
     * @param <T>    the type of the object
     * @return the play in flying packet
     * @throws WrongTypeException thrown if the provided {@link T} object is of a wrong type
     */
    public abstract <T> PlayInFlyingPacket toFlyingPacket(Player player, T object) throws WrongTypeException;

}