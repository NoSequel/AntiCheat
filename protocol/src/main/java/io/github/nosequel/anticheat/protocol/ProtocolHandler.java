package io.github.nosequel.anticheat.protocol;

import io.github.nosequel.anticheat.protocol.tracker.PlayerStateTracker;

public class ProtocolHandler {

    private static ProtocolHandler instance;

    private final PacketHandler packetHandler;
    private final PlayerStateTracker playerStateTracker;

    /**
     * Constructor to make a new protocol handler object
     *
     * @param packetHandler      the packet handler to register
     * @param playerStateTracker a tracker to get nms-specific player data from
     */
    public ProtocolHandler(PacketHandler packetHandler, PlayerStateTracker playerStateTracker) {
        instance = this;

        this.packetHandler = packetHandler;
        this.playerStateTracker = playerStateTracker;
    }

    /**
     * Get the instance of the protocol handler
     *
     * @return the instance
     */
    public static ProtocolHandler getInstance() {
        return instance;
    }

    /**
     * Get the packet handler
     *
     * @return the packet handler
     */
    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    /**
     * Get the player state tracker
     *
     * @return the tracker
     */
    public PlayerStateTracker getPlayerStateTracker() {
        return playerStateTracker;
    }
}
