package io.github.nosequel.anticheat.shared.data;

import io.github.nosequel.anticheat.shared.check.Check;

public abstract class CheckData<T extends Check> {

    /**
     * Get the type of the check
     *
     * @return the type
     */
    public abstract Class<T> getType();

}
