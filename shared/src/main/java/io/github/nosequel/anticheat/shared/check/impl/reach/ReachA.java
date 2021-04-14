package io.github.nosequel.anticheat.shared.check.impl.reach;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.check.impl.reach.data.ReachAData;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;

public class ReachA extends Check<ReachA, ReachAData> {

    public ReachA(PlayerDataHandler handler) {
        super(handler);
    }

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link PlayerData} object
     *
     * @param checkData the data of the player
     * @param packet    the packet to handle
     */
    @Override
    public void handle(ReachAData checkData, Packet packet) {

    }

    /**
     * Create a new data object for ap layer
     *
     * @param playerData the player to create it for
     * @return the newly created data
     */
    @Override
    public PlayerCheckData<?> createData(PlayerData playerData) {
        return new ReachAData();
    }
}
