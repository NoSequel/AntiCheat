package io.github.nosequel.anticheat.shared.check.impl.speed.data;

import io.github.nosequel.anticheat.shared.check.Check;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class SpeedData<T extends Check> extends PlayerCheckData<T> {

    private double lastDistance;

    private boolean lastOnGround;

    private double lastX;
    private double lastY;
    private double lastZ;

    private double blockFriction;

    /**
     * Setup the data in the check
     */
    @Override
    public void setup() {
        this.blockFriction = 0.91F;
    }
}
