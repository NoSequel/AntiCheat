package io.github.nosequel.anticheat.shared.check.impl;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.check.CheckData;

@CheckData(name = "debug")
public class DebugCheck extends Check {

    /**
     * Handle a packet
     *
     * @param packet the packet to handle
     */
    @Override
    public void handle(Packet packet) {
        if(packet instanceof PlayInFlyingPacket) {
            this.flag(packet.getPlayer(), "the player is cheater");
        } else {
            this.flag(packet.getPlayer(), "hes cheating because iti snt lay in flying packet...");
        }
    }
}
