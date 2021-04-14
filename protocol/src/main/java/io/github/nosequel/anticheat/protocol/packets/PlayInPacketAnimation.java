package io.github.nosequel.anticheat.protocol.packets;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.PacketType;
import org.bukkit.entity.Player;

public class PlayInPacketAnimation extends Packet {

    private final boolean digging;

    /**
     * Constructor to make a new {@link PlayInPacketAnimation}
     *
     * @param player  the player who the packet is for
     * @param time    the time the packet was executed
     * @param digging whether it's a digging packet or not
     */
    public PlayInPacketAnimation(Player player, long time, boolean digging) {
        super(PacketType.INBOUND, player, time);
        this.digging = digging;
    }

    /**
     * Check if the packet is a digging packet
     *
     * @return the packet
     */
    public boolean isDigging() {
        return digging;
    }
}
