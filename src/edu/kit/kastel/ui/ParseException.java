package edu.kit.kastel.ui;

/**
 * Exception thrown when parsing fails.
 *
 * @author uupyx
 */
public class ParseException extends Exception {
    /**
     * Constructs a ParseException with the given message.
     *
     * @param message the error message.
     */
    public ParseException(String message) {
        super(message);
    }
}
