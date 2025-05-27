package edu.kit.kastel.model;

import java.util.Objects;

/**
 * Represents a directed edge between two nodes.
 *
 * @author uupyx
 */
public class Edge {

    private static final String DISPLAY_PREFIX = "-[";
    private static final String DISPLAY_SUFFIX = "]->";

    private static final String DOT_ARROW = " -> ";
    private static final String DOT_LABEL_PREFIX = " [label=";
    private static final String DOT_LABEL_SUFFIX = "]";

    /** The source node. */
    public final Node source;
    /** The target node. */
    public final Node target;
    /** The predicate string. */
    public final Predicate predicate;

    /**
     * Constructs an edge.
     *
     * @param source the source node.
     * @param target the target node.
     * @param predicate the predicate.
     */
    public Edge(Node source, Node target, Predicate predicate) {
        this.source = source;
        this.target = target;
        this.predicate = predicate;
    }

    /**
     * Returns a display string for the edge.
     *
     * @return display string.
     */
    public String getDisplayString() {
        return source.getDisplayName() + DISPLAY_PREFIX + predicate.getName() + DISPLAY_SUFFIX + target.getDisplayName();
    }

    /**
     * Returns the DOT notation string for the edge.
     *
     * @return DOT string.
     */
    public String getDotString() {
        return source.name.toLowerCase() + DOT_ARROW + target.name.toLowerCase() + DOT_LABEL_PREFIX + predicate.getLabel()
                + DOT_LABEL_SUFFIX;
    }

    /**
     * Returns an integer representing the predicate order.
     *
     * @return predicate order.
     */
    public int getPredicateOrder() {
        return predicate.getOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) o;
        return source.equals(other.source) && target.equals(other.target) && predicate.equals(other.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, predicate);
    }
}
