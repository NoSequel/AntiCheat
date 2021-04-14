package io.github.nosequel.anticheat.protocol.tracker;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class PlayerStateTracker {

    /**
     * Get the friction of the location's block
     *
     * @param location the location to get the friction from
     * @return the friction
     */
    public abstract double getFriction(Location location);

    /**
     * Check if a player is on the ground
     *
     * @param player the player to check
     * @return whether they are on the ground or not
     */
    public abstract boolean isOnGround(Player player);

    /**
     * Get the movement speed of a player
     *
     * @param player the player to get the speed of
     * @return the movement speed
     */
    public abstract double getMovementSpeed(Player player);

}
