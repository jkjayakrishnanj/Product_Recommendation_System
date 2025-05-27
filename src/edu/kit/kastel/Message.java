package edu.kit.kastel;

/**
 * Enum representing different message formats and methods to print them. Provides methods to print formatted messages to the standard
 * output and error streams.
 *
 * This enum cannot be instantiated and contains predefined message formats.
 *
 * @author uupyx
 */
public enum Message {

    /**
     * Message for an unknown command.
     */
    UNKNOWN_COMMAND("Error, Unknown command"),

    /**
     * Message for an invalid edge relationship.
     */
    INVALID_EDGE("Error, specified edge has an invalid relationship"),

    /**
     * Message for invalid 'add' command syntax.
     */
    INVALID_SYNTAX("Error, Invalid add command syntax"),

    /**
     * Start of a digraph.
     */
    DIGRAPH_START("digraph {"),

    /**
     * End of a digraph.
     */
    DIGRAPH_END("}"),

    /**
     * Node shape in digraph visualization.
     */
    DIGRAPH_SHAPE("%s [shape=box]"),

    /**
     * Message when file path is required for loading database.
     */
    FILE_PATH_REQUIRED("Error, load database command requires a file path"),

    /**
     * Message when the specified file does not exist.
     */
    FILE_DOES_NOT_EXIST("Error, specified database file does not exist"),

    /**
     * Message for invalid format in a line.
     */
    INVALID_FORMAT("Error, Invalid format in line: %s"),

    /**
     * Message for invalid edge relationships.
     */
    INVALID_RELATIONSHIP("Error, specified edge has an invalid relationship"),

    /**
     * Generic error message.
     */
    ERROR("Error, %s"),

    /**
     * Message for invalid 'remove' command syntax.
     */
    INVALID_SYNTAX_REMOVE("Error, invalid remove command syntax"),

    /**
     * Message when edge is not found.
     */
    EDGE_NOT_FOUND("Error, edge not found"),

    /**
     * Message for unknown predicate.
     */
    UNKNOWN_PREDICATE("Error, unknown predicate %s"),

    /**
     * Message when product already exists with a different name.
     */
    ALREADY_EXISTS("Error, Product with id %d already exists with different name"),

    /**
     * Message for inconsistent product ID.
     */
    INCONSISTENT_ID("Error, inconsistent product id for %s"),

    /**
     * Message for echoing input.
     */
    ECHO("%s");

    private final String format;

    Message(String format) {
        this.format = format;
    }

    /**
     * Prints a formatted message to the standard output stream.
     *
     * @param args the arguments to be inserted into the message format
     */
    public void print(Object... args) {
        System.out.println(String.format(format, args));
    }

    /**
     * Prints a formatted message to the standard error stream.
     *
     * @param args the arguments to be inserted into the message format
     */
    public void error(Object... args) {
        System.err.println(String.format(format, args));
    }
}
