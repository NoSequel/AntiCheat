package io.github.nosequel.anticheat.protocol;

public interface PacketListener {

    /**
     * Handle a packet
     *
     * @param packet the packet to handle
     */
    void handle(Packet packet);

}
