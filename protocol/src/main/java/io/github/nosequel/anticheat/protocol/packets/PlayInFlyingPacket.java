package io.github.nosequel.anticheat.protocol.packets;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.PacketType;
import org.bukkit.entity.Player;

public class PlayInFlyingPacket extends Packet {

    private final double x;
    private final double y;
    private final double z;

    private final float yaw;
    private final float pitch;

    /**
     * Constructor to make a new {@link Packet} object
     *
     * @param player the player who the packet is for
     * @param time   the time the packet was sent
     * @param x      the x axis of the player
     * @param y      the y axis of the player
     * @param z      the z axis of the player
     * @param yaw    the yaw of the player's camera
     * @param pitch  the pitch of the player's camera
     */
    public PlayInFlyingPacket(Player player, long time, double x, double y, double z, float yaw, float pitch) {
        super(PacketType.INBOUND, player, time);

        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Get the x axis of the packet
     *
     * @return the x axis
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y axis of the packet
     *
     * @return the y axis
     */
    public double getY() {
        return y;
    }

    /**
     * Get the z axis of the packet
     *
     * @return the z axis
     */
    public double getZ() {
        return z;
    }

    /**
     * Get the yaw of the player's camera
     *
     * @return the yaw
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Get the pitch of the player's camera
     *
     * @return the pitch
     */
    public float getPitch() {
        return pitch;
    }
}