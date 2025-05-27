package edu.kit.kastel.model;

import java.util.Set;

import edu.kit.kastel.ui.ParseException;

/**
 * Interface for recommendation terms.
 *
 * @author uupyx
 */
public interface Term {
    /**
     * Evaluates the term on the given graph.
     *
     * @param graph the graph.
     * @return a set of recommended products.
     * @throws ParseException if evaluation fails.
     */
    Set<Product> evaluate(Graph graph) throws ParseException;
}
