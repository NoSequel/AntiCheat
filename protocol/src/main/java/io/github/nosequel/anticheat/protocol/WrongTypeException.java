package io.github.nosequel.anticheat.protocol;

public class WrongTypeException extends Exception {

    private final Class<?> context;

    /**
     * Constructor to make a new wrong type exception
     *
     * @param message the message to output
     * @param context the context of the exception
     */
    public WrongTypeException(String message, Class<?> context) {
        super(message);
        this.context = context;
    }

    @Override
    public String getMessage() {
        return "At " + context.getName() + " " + super.getMessage();
    }
}