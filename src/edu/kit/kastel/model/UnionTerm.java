package edu.kit.kastel.model;

import java.util.HashSet;
import java.util.Set;

import edu.kit.kastel.ui.ParseException;

/**
 * Represents the union of two recommendation terms.
 *
 * @author uupyx
 */
public class UnionTerm implements Term {
    private final Term left;
    private final Term right;

    /**
     * Constructs a UnionTerm.
     *
     * @param left the left term.
     * @param right the right term.
     */
    public UnionTerm(Term left, Term right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the union term.
     *
     * @param graph the graph.
     * @return the union of the two term evaluations.
     * @throws ParseException if evaluation fails.
     */
    @Override
    public Set<Product> evaluate(Graph graph) throws ParseException {
        Set<Product> result = new HashSet<>(left.evaluate(graph));
        result.addAll(right.evaluate(graph));
        return result;
    }
}
