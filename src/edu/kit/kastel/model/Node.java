package edu.kit.kastel.model;

/**
 * Abstract class representing a node.
 *
 * @author uupyx
 */
public abstract class Node {
    /** The name of the node. */
    public String name;

    /**
     * Constructs a node with the given name.
     *
     * @param name the node name.
     */
    public Node(String name) {
        this.name = name;
    }

    /**
     * Returns the display name.
     *
     * @return display name.
     */
    public abstract String getDisplayName();

    /**
     * Returns the sort key.
     *
     * @return sort key.
     */
    public abstract String getSortKey();

    /**
     * Returns the name.
     *
     * @return name.
     */
    public abstract String getName();
}
