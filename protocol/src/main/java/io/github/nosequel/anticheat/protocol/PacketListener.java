package io.github.nosequel.anticheat.protocol;

public interface PacketListener {

    /**
     * Handle a packet
     *
     * @param packet the packet to hand;e
     */
    void handle(Packet packet);

}
