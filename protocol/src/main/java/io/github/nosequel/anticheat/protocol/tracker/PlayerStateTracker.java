package io.github.nosequel.anticheat.protocol.tracker;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class PlayerStateTracker {

    /**
     * Get the angle between 2 locations
     *
     * @param distanceX the X distance between the 2 locations
     * @param distanceZ the Z distance between the 2 locations
     * @param yaw       the yaw to get the angle from
     * @return the angle
     */
    public double getMoveAngle(double distanceX, double distanceZ, double yaw) {
        return Math.abs(this.wrapAngleTo180((float) ((Math.toDegrees(Math.atan2(distanceZ, distanceX)) - 90) - yaw)));
    }

    /**
     * Wrap a degree measure to 180 degrees.
     * <p>
     * Used for yaw detection and
     * angle calculation.
     *
     * @param value the value to wrap
     * @return the wrapped value
     */
    public float wrapAngleTo180(float value) {
        value %= 360;

        if(value >= 180.0F) {
            value -= 360.0F;
        }

        if(value < -180.0F) {
            value += 360.0F;
        }

        return value;
    }

    /**
     * Check if a location is underneath a block
     *
     * @param location the location to check
     * @return whether its under a block or not
     */
    public boolean isUnderBlock(Location location) {
        return !location.clone().add(0, 1, 0).getBlock().getType().equals(Material.AIR);
    }

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
