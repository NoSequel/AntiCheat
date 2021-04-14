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
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

@CheckData(name = "Speed A", flagThreshold = 20)
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

            final double horizontalOffset = Math.hypot(packet.getX() - data.getLastX(), packet.getZ() - data.getLastZ());
            final double verticalOffset = (packet.getY() - data.getLastY());

            double jumpHeight = 0.42 + this.getJumpBoost(player) * 0.1;
            double movementSpeed = tracker.getMovementSpeed(player);

            if (tracker.isOnGround(player)) {
                movementSpeed *= 1.3;
                movementSpeed *= 0.16277136 / Math.pow(data.getBlockFriction(), 3);

                if (verticalOffset > 0.00001 && verticalOffset < jumpHeight) {
                    movementSpeed += 0.2;
                }
            } else {
                movementSpeed = 0.026;
                data.setBlockFriction(0.91);
            }

            final double friction = (horizontalOffset - data.getLastDistance()) / movementSpeed;

            if (friction >= 1.0D) {
                this.flag(player, "friction is too high (" + friction + ")");
            }

            data.setLastDistance(horizontalOffset * data.getBlockFriction());
            data.setBlockFriction(tracker.getFriction(new Location(player.getWorld(), data.getLastX(), data.getLastY(), data.getLastZ())) * 0.91);

            this.updateData(data, packet);
        }
    }

    /**
     * Get the jump boost value of the player
     *
     * @param player the player to get the jump boost of
     * @return the jump boost value
     */
    private double getJumpBoost(Player player) {
        return player.getActivePotionEffects().stream()
                .filter(potionEffect -> potionEffect.getType().equals(PotionEffectType.JUMP))
                .map(potionEffect -> potionEffect.getAmplifier() + 1)
                .findFirst().orElse(0);
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
    private void updateData(SpeedAData data, PlayInFlyingPacket packet) {
        final Player player = packet.getPlayer();

        data.setLastX(packet.getX());
        data.setLastY(packet.getY());
        data.setLastZ(packet.getZ());

        data.setLastOnGround(player.isOnGround());
    }
}