package io.github.nosequel.anticheat.shared.check.impl.autoclicker.data;

import io.github.nosequel.anticheat.shared.check.impl.autoclicker.AutoclickerCheck;
import io.github.nosequel.anticheat.shared.data.PlayerCheckData;

import java.util.ArrayList;
import java.util.List;

public class AutoclickerData extends PlayerCheckData<AutoclickerCheck> {

    private final List<ClickData> clicks = new ArrayList<>();

    /**
     * Update the clicks list
     */
    public void updateClicks() {
        this.clicks.removeIf(click -> click.getExpiration() < System.currentTimeMillis());
    }

    /**
     * Add a new click to the list of clicks
     */
    public void addClick() {
        this.clicks.add(new ClickData(System.currentTimeMillis() + 1000));
    }

    /**
     * Get the amount of clicks in the list which are roughly equal to each other.
     * <p>
     * Used for consistency checks, simply get the amount of
     * clicks which are nearby each other.
     *
     * @param margin the margin which can be in between the clicks
     * @return the amount of roughly equal clicks
     */
    public int getRoughlyEqualAmount(int margin) {
        int equalAmount = 0;
        ClickData previousClick = null;

        for(ClickData click : this.clicks) {
            if (previousClick == null) {
                previousClick = click;
                continue;
            }

            if (this.isRoughlyEqual((int) click.getExpiration(), (int) previousClick.getExpiration(), margin)) {
                equalAmount += 1;
            }


            previousClick = click;
        }

        return equalAmount;
    }

    /**
     * Get the amount of clicks registered
     *
     * @return the amount of clicks
     */
    public int getAmount() {
        return this.clicks.size();
    }

    /**
     * Check if a number is roughly equal to another number
     *
     * @param a      the first number
     * @param b      the second number
     * @param margin the margin which can be in between the numbers
     * @return whether it's roughly equal or not
     */
    private boolean isRoughlyEqual(int a, int b, int margin) {
        final int equality = Math.abs((a-b));

        if(equality == 0) {
            return false;
        }

        return equality < margin;
    }

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
    public Class<AutoclickerCheck> getType() {
        return AutoclickerCheck.class;
    }
}