package io.github.nosequel.anticheat.shared.check.impl.speed.data;

import io.github.nosequel.anticheat.shared.check.impl.speed.SpeedA;
import io.github.nosequel.anticheat.shared.data.CheckData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpeedAData extends CheckData<SpeedA> {

    private double lastDistance;

    private boolean lastChecked;
    private boolean lastOnGround;

    private double lastX;
    private double lastY;
    private double lastZ;

    /**
     * Get the type of the check
     *
     * @return the type
     */
    @Override
    public Class<SpeedA> getType() {
        return SpeedA.class;
    }
}
