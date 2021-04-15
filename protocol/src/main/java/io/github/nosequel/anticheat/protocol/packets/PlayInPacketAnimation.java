package io.github.nosequel.anticheat.protocol.packets;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.PacketType;
import org.bukkit.entity.Player;

public class PlayInPacketAnimation extends Packet {

    /**
     * Constructor to make a new {@link PlayInPacketAnimation}
     *
     * @param player  the player who the packet is for
     * @param time    the time the packet was executed
     */
    public PlayInPacketAnimation(Player player, long time) {
        super(PacketType.INBOUND, player, time);
    }

}
