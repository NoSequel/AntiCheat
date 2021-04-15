package io.github.nosequel.anticheat.shared.check.impl.speed;

import io.github.nosequel.anticheat.protocol.Packet;
import io.github.nosequel.anticheat.protocol.ProtocolHandler;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import io.github.nosequel.anticheat.protocol.tracker.PlayerStateTracker;
import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.check.CheckData;
import io.github.nosequel.anticheat.shared.check.impl.speed.data.impl.SpeedAData;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;
import io.github.nosequel.anticheat.shared.data.PlayerData;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import org.bukkit.entity.Player;

/**
 * General idea taken from Nemesis (sim0n's anticheat), https://github.com/sim0n/Nemesis/blob/main/src/main/java/dev/sim0n/anticheat/check/impl/speed/Speed.java.
 */
@CheckData(name = "Speed A", flagThreshold = 5, experimental = true)
public class SpeedA extends Check<SpeedA, SpeedAData> {

    public SpeedA(PlayerDataHandler handler) {
        super(handler);
    }

    /**
     * Handle a packet by a player
     * <p>
     * Called from the PacketListener#handle method,
     * but with a provided {@link PlayerData} object
     *
     * @param data the data of the player
     * @param object    the packet to handle
     */
    @Override
    public void handle(SpeedAData data, Packet object) {
        if (object instanceof PlayInFlyingPacket) {
            final Player player = object.getPlayer();
            final PlayInFlyingPacket packet = (PlayInFlyingPacket) object;
            final PlayerStateTracker tracker = ProtocolHandler.getInstance().getPlayerStateTracker();

            final double distanceX = packet.getX() - data.getLastX();
            final double distanceZ = packet.getZ() - data.getLastZ();

            final double horizontalOffset = (distanceX * distanceX) + (distanceZ * distanceZ);

            double movementSpeed = tracker.getMovementSpeed(player);
            double friction = 0.91 * tracker.getFriction(player.getLocation());

            movementSpeed *= 1.3F;
            movementSpeed *= 0.16277136F / Math.pow(friction, 3);

            if (tracker.getMoveAngle(distanceX, distanceZ, packet.getYaw()) > 135) {
                movementSpeed /= 1.05F;
            }

            double threshold = (horizontalOffset - data.getLastDistance()) / movementSpeed;

            if (data.isLastOnGround()) {
                if (threshold >= 1.2D) {
                    this.flag(player, "speed is too high (" + threshold + ")");
                }
            }

            data.setLastDistance(horizontalOffset * friction);
            this.updateData(data, packet, tracker);
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
        return new SpeedAData();
    }

    /**
     * Update the data of the player
     *
     * @param data   the data to update
     * @param packet the packet to update the data to
     */
    private void updateData(SpeedAData data, PlayInFlyingPacket packet, PlayerStateTracker tracker) {
        final Player player = packet.getPlayer();

        data.setLastX(packet.getX());
        data.setLastY(packet.getY());
        data.setLastZ(packet.getZ());

        data.setLastOnGround(tracker.isOnGround(player));
    }
}