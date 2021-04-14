package io.github.nosequel.anticheat.shared.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CheckData {

    /**
     * Get the name of the check
     *
     * @return the name
     */
    String name();

    /**
     * Get the description of the check
     *
     * @return the description
     */
    String description() default "";

    /**
     * Get the max amount of violations
     *
     * @return the max amount of violations
     */
    int maxViolations() default 20;

    /**
     * Get the max threshold to flag
     *
     * @return the threshold
     */
    int flagThreshold() default 10;

    /**
     * Check if the check has auto-ban enabled
     *
     * @return whether it does or not
     */
    boolean autoBan() default false;

    /**
     * Check if the check is an experimental check.
     * <p>
     * This only prints the output to the
     * developers and/or the console.
     *
     * @return whether it's an experimental check or not
     */
    boolean experimental() default false;

}
