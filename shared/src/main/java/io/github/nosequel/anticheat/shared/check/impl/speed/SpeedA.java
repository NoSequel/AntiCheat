package io.github.nosequel.anticheat.shared.check.impl.speed;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.check.CheckData;
import io.github.nosequel.anticheat.shared.check.impl.speed.data.SpeedAData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import org.bukkit.entity.Player;

@CheckData(name = "Speed A")
public class SpeedA extends Check {

    private final float friction = 0.91F;

    public SpeedA(PlayerDataHandler handler) {
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
            final SpeedAData data = (SpeedAData) playerData.findOrRegisterData(SpeedA.class, new SpeedAData());

            final Player player = object.getPlayer();
            final PlayInFlyingPacket packet = (PlayInFlyingPacket) object;

            if (!data.isLastChecked()) {
                this.updateData(data, packet);
                return;
            }

            final double distanceX = packet.getX() - data.getLastX();
            final double distanceZ = packet.getZ() - data.getLastZ();

            final double distance = (distanceX * distanceX) + (distanceZ * distanceZ);

            if (!player.isOnGround() && !data.isLastOnGround()) {
                final double lastDistance = data.getLastDistance();

                final double shiftedDistance = lastDistance * friction;
                final double equality = (distance - shiftedDistance) * 29;

                if (equality >= 9.0) {
                    this.flag(player, "friction is too high (" + equality + "/9.0)");
                }
            }

            data.setLastDistance(distance);
            this.updateData(data, packet);
        }
    }

    /**
     * Update the data of the player
     *
     * @param data   the data to update
     * @param packet the packet to update the data to
     */
    private void updateData(SpeedAData data, PlayInFlyingPacket packet) {
        final Player player = packet.getPlayer();

        data.setLastX(packet.getX());
        data.setLastY(packet.getY());
        data.setLastZ(packet.getZ());

        data.setLastOnGround(player.isOnGround());

        data.setLastChecked(true);
    }
}
