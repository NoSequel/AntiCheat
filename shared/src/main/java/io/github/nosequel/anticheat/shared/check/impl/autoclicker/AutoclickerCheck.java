package io.github.nosequel.anticheat.shared.check.impl.autoclicker;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.packets.PlayInPacketAnimation;
import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.check.impl.autoclicker.data.AutoclickerData;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;

public abstract class AutoclickerCheck extends Check<AutoclickerCheck, AutoclickerData> {

    public AutoclickerCheck(PlayerDataHandler handler) {
        super(handler);
    }

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link io.github.nosequel.anticheat.shared.data.PlayerData} object
     *
     * @param checkData the data of the player
     * @param packet    the packet to handle
     */
    public abstract void handle(AutoclickerData checkData, PlayInPacketAnimation packet);

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link io.github.nosequel.anticheat.shared.data.PlayerData} object
     *
     * @param object the packet to handle
     */
    @Override
    public final void handle(Packet object) {
        if (object instanceof PlayInPacketAnimation) {
            final PlayerData playerData = super.getHandler().find(object.getPlayer());
            final PlayInPacketAnimation packet = (PlayInPacketAnimation) object;

            AutoclickerData data = (AutoclickerData) playerData.findData(AutoclickerCheck.class);

            if (data == null) {
                data = (AutoclickerData) this.createData(playerData);

                data.setup();
                playerData.registerData(data);
            }

            data.addClick();
            data.updateClicks();

            this.handle(data, packet);
        }
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
    public void handle(AutoclickerData checkData, Packet packet) {
        // empty !!!
    }
}