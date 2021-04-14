package io.github.nosequel.anticheat.shared.check.impl;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.check.CheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;

@CheckData(name = "debug")
public class DebugCheck extends Check {

    public DebugCheck(PlayerDataHandler handler) {
        super(handler);
    }

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link PlayerData} object
     *
     * @param playerData the player data
     * @param object     the packet to handle
     */
    @Override
    public void handle(PlayerData playerData, Packet object) {
        if (object instanceof PlayInFlyingPacket) {

        }
    }
}