package edu.kit.kastel.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.kit.kastel.Message;

/**
 * Graph data structure representing nodes and edges.
 *
 * @author uupyx
 */
public class Graph {

    private final Set<Edge> edges;
    private final Set<Node> nodes;
    private final Map<Integer, Product> productsById;

    /**
     * A variable, which shows whether the graph is completely loaded from a database or not.
     */
    private boolean loaded;

    /**
     * Constructs an empty Graph.
     */
    public Graph() {
        nodes = new HashSet<>();
        edges = new HashSet<>();
        productsById = new HashMap<>();
    }

    /**
     * Adds an edge to the graph and creates its inverse if necessary.
     *
     * @param e the edge to add.
     * @return true if added successfully, false otherwise.
     */
    public boolean addEdge(Edge e) {
        if (e.source.equals(e.target) || !e.predicate.validate(e.source, e.target)) {
            return false;
        }
        addNode(e.source);
        addNode(e.target);
        if (edges.contains(e)) {
            return true;
        }

        edges.add(e);
        Predicate inversePredicate = e.predicate.getInverse();
        if (inversePredicate == null) {
            Message.UNKNOWN_PREDICATE.error(e.predicate.name());
            return false;
        }
        Edge inverse = new Edge(e.target, e.source, inversePredicate);
        if (!edges.contains(inverse)) {
            edges.add(inverse);
        }

        return true;
    }

    /**
     * Removes an edge and its inverse from the graph.
     *
     * @param e the edge to remove.
     * @return true if the edge was removed, false otherwise.
     */
    public boolean removeEdge(Edge e) {
        boolean removed = edges.remove(e);
        Predicate inversePredicate = e.predicate.getInverse();
        Edge inverse = new Edge(e.target, e.source, inversePredicate);
        edges.remove(inverse);
        removeIsolated(e.source);
        removeIsolated(e.target);
        return removed;
    }

    /**
     * Removes a node from the graph if it has no connected edges.
     *
     * @param n the node to potentially remove.
     */
    private void removeIsolated(Node n) {
        boolean hasEdge = false;
        for (Edge edge : edges) {
            if (edge.source.equals(n) || edge.target.equals(n)) {
                hasEdge = true;
                break;
            }
        }
        if (!hasEdge) {
            nodes.remove(n);
            if (n instanceof Product) {
                productsById.remove(((Product) n).id);
            }
        }
    }

    /**
     * Adds a node to the graph if it does not already exist.
     *
     * @param n the node to add.
     */
    private void addNode(Node n) {
        if (n instanceof Product) {
            Product newProduct = (Product) n;
            if (productsById.containsKey(newProduct.id)) {
                Product existing = productsById.get(newProduct.id);
                if (!existing.name.equalsIgnoreCase(newProduct.name)) {
                    Message.ALREADY_EXISTS.error(newProduct.id);
                    return;
                }
                return;
            }
        }

        boolean exists = false;
        for (Node node : nodes) {
            if (node.name.equalsIgnoreCase(n.name)) {
                exists = true;
                if ((node instanceof Product) && (n instanceof Product)) {
                    Product existing = (Product) node;
                    Product newProduct = (Product) n;
                    if (existing.id != newProduct.id) {
                        Message.INCONSISTENT_ID.error(n.name);
                    }
                }
                break;
            }
        }
        if (!exists) {
            nodes.add(n);
            if (n instanceof Product) {
                productsById.put(((Product) n).id, (Product) n);
            }
        }
    }

    /**
     * Returns all nodes in sorted order.
     *
     * @return sorted set of nodes.
     */
    public Set<Node> getNodes() {
        TreeSet<Node> sorted = new TreeSet<>(Comparator.comparing(n -> n.name.toLowerCase()));
        sorted.addAll(nodes);
        return sorted;
    }

    /**
     * Returns all edges in sorted order.
     *
     * @return sorted set of edges.
     */
    public Set<Edge> getEdges() {
        TreeSet<Edge> sorted = new TreeSet<>(Comparator.comparing((Edge e) -> e.source.getSortKey())
                .thenComparing(e -> e.target.getSortKey()).thenComparing(e -> e.getPredicateOrder()));
        sorted.addAll(edges);

        return sorted;
    }

    /**
     * Returns all category nodes in sorted order.
     *
     * @return sorted set of categories.
     */
    public Set<Category> getCategories() {
        TreeSet<Category> sorted = new TreeSet<>(Comparator.comparing(c -> c.name.toLowerCase()));
        for (Node n : nodes) {
            if (n instanceof Category) {
                sorted.add((Category) n);
            }
        }
        return sorted;
    }

    /**
     * Returns all outgoing edges from a given source node that match the specified predicate.
     *
     * @param source the source node.
     * @param predicate the predicate to match.
     * @return set of matching edges.
     */
    public Set<Edge> getOutgoingEdges(Node source, Predicate predicate) {
        Set<Edge> set = new HashSet<>();
        for (Edge e : edges) {
            if (e.source.equals(source) && e.predicate.equals(predicate)) {
                set.add(e);
            }
        }
        return set;
    }

    /**
     * Returns all outgoing edges from a given source node.
     *
     * @param source the source node.
     * @return set of outgoing edges.
     */
    public Set<Edge> getOutgoingEdges(Node source) {
        Set<Edge> set = new HashSet<>();
        for (Edge e : edges) {
            if (e.source.equals(source)) {
                set.add(e);
            }
        }
        return set;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID.
     * @return the product, or null if not found.
     */
    public Product getProductById(int id) {
        return productsById.get(id);
    }

    /**
     * Resets the graph. Removes every entry.
     */
    public void reset() {
        this.edges.clear();
        this.nodes.clear();
        this.productsById.clear();
    }

    /**
     * Checks if the database has finished loading.
     *
     * @return true if the database has loaded, false otherwise
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Sets the loading state of the database.
     *
     * @param loaded true if the database has finished loading, false otherwise
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
