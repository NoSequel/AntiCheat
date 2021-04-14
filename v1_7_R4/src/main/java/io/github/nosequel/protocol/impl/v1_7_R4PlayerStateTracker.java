package io.github.nosequel.protocol.impl;

import io.github.nosequel.anticheat.protocol.tracker.PlayerStateTracker;
import lombok.SneakyThrows;
import net.minecraft.server.v1_7_R4.AttributeModifiable;
import net.minecraft.server.v1_7_R4.AttributeModifier;
import net.minecraft.server.v1_7_R4.Block;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_7_R4PlayerStateTracker extends PlayerStateTracker {


    @Override
    public double getFriction(Location original) {
        final Location location = original.subtract(0, 1, 0);
        final Block block = ((CraftWorld) location.getWorld()).getHandle().getType(location.getBlockX(), location.getBlockY(), location.getBlockZ());

        return block.frictionFactor;
    }

    /**
     * Check if a player is on the ground
     *
     * @param player the player to check
     * @return whether they are on the ground or not
     */
    @Override
    public boolean isOnGround(Player player) {
        return ((CraftPlayer) player).getHandle().onGround;
    }

    /**
     * Get the movement speed of a player
     *
     * @param player the player to get the speed of
     * @return the movement speed
     */
    @SneakyThrows
    @Override
    public double getMovementSpeed(Player player) {
        final EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        final AttributeModifiable attribute = (AttributeModifiable) entityPlayer.getAttributeInstance(GenericAttributes.d);

        if(attribute == null) {
            throw new IllegalStateException(player.getName() + " does not have movement speed attribute");
        }

        double value = attribute.b();

        for(int i = 0; i < 3; i++) {
            for (Object object : attribute.a(i)) {
                final AttributeModifier modifier = (AttributeModifier) object;

                if (i == 0) {
                    value += modifier.d();
                } else if (i == 1) {
                    value += modifier.d() * attribute.b();
                } else if (player.isSprinting()) {
                    value += value * modifier.d();
                }
            }
        }

        return value;
    }
}
