package io.github.nosequel.anticheat.shared.data;

import io.github.nosequel.anticheat.shared.check.Check;

public abstract class PlayerCheckData<T extends Check> {

    /**
     * Setup the data in the check
     */
    public abstract void setup();

    /**
     * Get the type of the check
     *
     * @return the type
     */
    public abstract Class<T> getType();

}
