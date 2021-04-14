package io.github.nosequel.protocol.impl;

import io.github.nosequel.anticheat.protocol.PacketHandler;
import io.github.nosequel.anticheat.protocol.WrongTypeException;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import net.minecraft.server.v1_7_R4.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class v1_7_R4PacketHandler extends PacketHandler {

    /**
     * Convert an object to a {@link PlayInFlyingPacket}.
     *
     * @param object the object to convert
     * @return the play in flying packet
     */
    @Override
    public <T> PlayInFlyingPacket toFlyingPacket(Player player, T object) throws WrongTypeException {
        if (!(object instanceof PacketPlayInFlying)) {
            throw new WrongTypeException(object.getClass() + " is not PacketPlayInFlying", this.getClass());
        }

        final PacketPlayInFlying packet = (PacketPlayInFlying) object;

        final double x = packet.c();
        final double y = packet.d();
        final double z = packet.e();

        final float yaw = packet.g();
        final float pitch = packet.h();

        return new PlayInFlyingPacket(player, System.currentTimeMillis(), x, y, z, yaw, pitch);
    }
}