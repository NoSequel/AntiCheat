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
     * Check if the check has auto-ban enabled
     *
     * @return whether it does or not
     */
    boolean autoBan() default false;

}
