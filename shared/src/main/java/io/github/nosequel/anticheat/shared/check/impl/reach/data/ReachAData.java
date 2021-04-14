package io.github.nosequel.anticheat.shared.check.impl.reach.data;

import io.github.nosequel.anticheat.shared.check.impl.reach.ReachA;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;

public class ReachAData extends PlayerCheckData<ReachA> {

    /**
     * Setup the data in the check
     */
    @Override
    public void setup() {

    }

    /**
     * Get the type of the check
     *
     * @return the type
     */
    @Override
    public Class<ReachA> getType() {
        return ReachA.class;
    }
}
