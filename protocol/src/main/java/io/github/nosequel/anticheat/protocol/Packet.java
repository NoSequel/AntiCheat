package io.github.nosequel.anticheat.protocol;

import org.bukkit.entity.Player;

public abstract class Packet {

    private final PacketType type;

    private final Player player;
    private final long time;

    /**
     * Constructor to make a new {@link Packet} object
     *
     * @param type   the type of the packet
     * @param player the player who the packet is for
     * @param time   the time the packet was sent
     */
    public Packet(PacketType type, Player player, long time) {
        this.type = type;
        this.player = player;
        this.time = time;
    }

    /**
     * Get the type of the packet
     *
     * @return the type of the packet
     */
    public PacketType getType() {
        return type;
    }

    /**
     * Get the player who the packet is for
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the time the packet was created
     *
     * @return the time
     */
    public long getTime() {
        return time;
    }
}