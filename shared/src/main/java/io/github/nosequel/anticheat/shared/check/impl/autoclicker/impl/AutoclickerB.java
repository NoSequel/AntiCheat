package io.github.nosequel.anticheat.shared.check.impl.autoclicker.impl;

import io.github.nosequel.anticheat.protocol.packets.PlayInPacketAnimation;
import io.github.nosequel.anticheat.shared.check.CheckData;
import io.github.nosequel.anticheat.shared.check.impl.autoclicker.AutoclickerCheck;
import io.github.nosequel.anticheat.shared.check.impl.autoclicker.data.AutoclickerData;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;

@CheckData(name = "AutoClicker B", flagThreshold = 1)
public class AutoclickerB extends AutoclickerCheck {

    public AutoclickerB(PlayerDataHandler handler) {
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
    public void handle(AutoclickerData checkData, PlayInPacketAnimation packet) {
        final int roughlyEqualAmount = checkData.getRoughlyEqualAmount(5);

        if(roughlyEqualAmount > 12) {
            this.flag(packet.getPlayer(), "clicks are too consistent (" + roughlyEqualAmount + ")");
        }
    }

    /**
     * Create a new data object for ap layer
     *
     * @param playerData the player to create it for
     * @return the newly created data
     */
    @Override
    public PlayerCheckData<?> createData(PlayerData playerData) {
        return new AutoclickerData();
    }
}
