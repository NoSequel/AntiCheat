package io.github.nosequel.anticheat.shared.check.impl.speed.data.impl;

import io.github.nosequel.anticheat.shared.check.impl.speed.SpeedA;
import io.github.nosequel.anticheat.shared.check.impl.speed.data.SpeedData;

public class SpeedAData extends SpeedData<SpeedA> {

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
