package io.github.nosequel.anticheat.protocol.packets.use;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.PacketType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PlayInUseEntityPacket extends Packet {

    private final Entity target;
    private final EntityUseAction action;

    /**
     * Constructor to make a new {@link Packet} object
     *
     * @param player the player who the packet is for
     * @param time   the time the packet was sent
     * @param target the entity who got interacted with
     * @param action the action of the packet
     */
    public PlayInUseEntityPacket(Player player, long time, Entity target, EntityUseAction action) {
        super(PacketType.INBOUND, player, time);

        this.target = target;
        this.action = action;
    }

    /**
     * Get the target entity
     *
     * @return the entity
     */
    public Entity getTarget() {
        return target;
    }

    /**
     * Get the action of the packet
     *
     * @return the action
     */
    public EntityUseAction getAction() {
        return action;
    }
}
