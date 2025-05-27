package edu.kit.kastel.model;

import java.util.HashSet;
import java.util.Set;

import edu.kit.kastel.ui.ParseException;

/**
 * Represents a final recommendation term.
 *
 * @author uupyx
 */
public class FinalTerm implements Term {

    private static final String ERR_PRODUCT = "Product id %d not found";
    private static final String STRATEGY_S1 = "S1";
    private static final String STRATEGY_S2 = "S2";
    private static final String STRATEGY_S3 = "S3";
    private static final String ERR_UNKNOWN_STRATEGY = "Unknown strategy ";

    private final String strategy;
    private final int productId;

    /**
     * Constructs a FinalTerm.
     *
     * @param strategy the recommendation strategy.
     * @param productId the product id.
     */
    public FinalTerm(String strategy, int productId) {
        this.strategy = strategy;
        this.productId = productId;
    }

    /**
     * Evaluates the final term.
     *
     * @param graph the graph.
     * @return set of recommended products.
     * @throws ParseException if evaluation fails.
     */
    @Override
    public Set<Product> evaluate(Graph graph) throws ParseException {
        Product ref = graph.getProductById(productId);
        if (ref == null) {
            throw new ParseException(String.format(ERR_PRODUCT, productId));
        }
        Set<Product> result = new HashSet<>();
        if (STRATEGY_S1.equals(strategy)) {
            for (Edge e : graph.getOutgoingEdges(ref, Predicate.CONTAINED_IN)) {
                for (Edge inEdge : graph.getOutgoingEdges(e.target, Predicate.CONTAINS)) {
                    if ((inEdge.target instanceof Product) && (!inEdge.target.equals(ref))) {
                        result.add((Product) inEdge.target);
                    }
                }
            }
        } else if (STRATEGY_S2.equals(strategy)) {
            result.addAll(dfs(graph, ref, Predicate.PREDECESSOR_OF, new HashSet<>()));
        } else if (STRATEGY_S3.equals(strategy)) {
            result.addAll(dfs(graph, ref, Predicate.SUCCESSOR_OF, new HashSet<>()));
        } else {
            throw new ParseException(ERR_UNKNOWN_STRATEGY + strategy);
        }

        result.remove(ref);

        return result;
    }

    private Set<Product> dfs(Graph graph, Product start, Predicate predicate, Set<Product> visited) {
        Set<Product> result = new HashSet<>();
        if (visited.contains(start)) {
            return result;
        }
        visited.add(start);
        for (Edge e : graph.getOutgoingEdges(start, predicate)) {
            if (e.target instanceof Product) {
                Product p = (Product) e.target;
                result.add(p);
                result.addAll(dfs(graph, p, predicate, visited));
            }
        }
        return result;
    }
}
