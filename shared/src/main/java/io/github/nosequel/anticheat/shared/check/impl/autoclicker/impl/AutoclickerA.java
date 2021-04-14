package io.github.nosequel.anticheat.shared.check.impl.autoclicker.impl;

import io.github.nosequel.anticheat.protocol.packets.PlayInPacketAnimation;
import io.github.nosequel.anticheat.shared.check.CheckData;
import io.github.nosequel.anticheat.shared.check.impl.autoclicker.AutoclickerCheck;
import io.github.nosequel.anticheat.shared.check.impl.autoclicker.data.AutoclickerData;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;

@CheckData(name = "AutoClicker A", flagThreshold = 15)
public class AutoclickerA extends AutoclickerCheck {

    public AutoclickerA(PlayerDataHandler handler) {
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
        if (checkData.getAmount() > 14) {
            this.flag(packet.getPlayer(), "high clicks per second (" + checkData.getAmount() + ")");
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