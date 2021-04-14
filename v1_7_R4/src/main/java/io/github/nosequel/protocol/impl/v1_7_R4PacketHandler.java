package io.github.nosequel.protocol.impl;

import io.github.nosequel.anticheat.protocol.PacketHandler;
import io.github.nosequel.anticheat.protocol.WrongTypeException;
import io.github.nosequel.anticheat.protocol.packets.PlayInFlyingPacket;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayInFlying;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import net.minecraft.util.io.netty.channel.Channel;
import net.minecraft.util.io.netty.channel.ChannelDuplexHandler;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelPipeline;
import net.minecraft.util.io.netty.channel.ChannelPromise;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class v1_7_R4PacketHandler extends PacketHandler {

    /**
     * Constructor to make a new packet handler.
     * <p>
     * This method automatically registers the
     * required listeners and other stuff required for
     * the packet handler.
     *
     * @param plugin the plugin to register the stuff to.
     */
    public v1_7_R4PacketHandler(JavaPlugin plugin) {
        super(plugin);
    }

    /**
     * Get the {@link PlayerConnection} of a player
     *
     * @param player the player to get the player connection object from
     * @return the object
     */
    private PlayerConnection getPlayerConnection(Player player) {
        return this.getEntityPlayer(player).playerConnection;
    }

    /**
     * Get the {@link EntityPlayer} object of a player
     *
     * @param player the player to get the entity player object from
     * @return the entity player
     */
    private EntityPlayer getEntityPlayer(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    /**
     * Setup the packet listening for a player.
     * <p>
     * Packet listening must be setup for the player
     * so we can listen to the player's checks.
     *
     * @param player the player to register it for
     */
    @Override
    public void setupPacketListening(Player player) {
        final PlayerConnection connection = this.getPlayerConnection(player);
        final NetworkManager networkManager = connection.networkManager;

        try {
            final Field channelField = networkManager.getClass().getDeclaredField("m");
            channelField.setAccessible(true);

            final Channel channel = (Channel) channelField.get(networkManager);
            final ChannelPipeline pipeline = channel.pipeline();

            pipeline.addBefore(
                    "packet_handler",
                    player.getName(),
                    new ChannelDuplexHandler() {
                        @Override
                        public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
                            if(packet instanceof Packet) {
                                listenToPacket(player, (Packet) packet);
                            }

                            super.write(context, packet, promise);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
                            if(packet instanceof Packet) {
                                listenToPacket(player, (Packet) packet);
                            }

                            super.channelRead(context, packet);
                        }
                    }
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listen to a packet
     *
     * @param player the player to listen to the packet for
     * @param packet the packet to listen to
     * @throws WrongTypeException thrown if one of the invoked methods has a wrong provided type
     */
    private void listenToPacket(Player player, Packet packet) throws WrongTypeException {
        if(packet instanceof PacketPlayInFlying) {
            final PacketPlayInFlying flying = (PacketPlayInFlying) packet;

            if(flying.j()) {
                this.handle(this.toFlyingPacket(player, packet));
            }
        }
    }

    /**
     * Disable packet listening for a player.
     * <p>
     * This should be called whenever the player disconnects
     * from the server, so the handler doesn't have to listen
     * to it's packets anymore.
     *
     * @param player the player to cancel it for
     */
    @Override
    public void cancelPacketListening(Player player) {
        // not required in this implementation.
    }

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