package edu.kit.kastel.model;

import java.util.Set;

import edu.kit.kastel.ui.ParseException;

/**
 * Represents the intersection of two recommendation terms.
 *
 * @author uupyx
 */
public class IntersectionTerm implements Term {
    private final Term left;
    private final Term right;

    /**
     * Constructs an IntersectionTerm.
     *
     * @param left the left term.
     * @param right the right term.
     */
    public IntersectionTerm(Term left, Term right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the intersection term.
     *
     * @param graph the graph.
     * @return the intersection of the two term evaluations.
     * @throws ParseException if evaluation fails.
     */
    @Override
    public Set<Product> evaluate(Graph graph) throws ParseException {
        Set<Product> leftSet = left.evaluate(graph);
        Set<Product> rightSet = right.evaluate(graph);
        leftSet.retainAll(rightSet);
        return leftSet;
    }
}
