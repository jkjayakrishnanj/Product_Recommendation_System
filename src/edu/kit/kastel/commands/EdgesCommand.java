package edu.kit.kastel.commands;

import java.util.Comparator;
import java.util.TreeSet;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Edge;
import edu.kit.kastel.model.Graph;

/**
 * Command to display all edges.
 *
 * @author uupyx
 */
public class EdgesCommand implements Command {
    private final Graph graph;

    /**
     * Constructs an EdgesCommand.
     *
     * @param graph the graph.
     */
    public EdgesCommand(Graph graph) {
        this.graph = graph;
    }

    /**
     * Executes the edges command.
     */
    @Override
    public void execute() {
        TreeSet<Edge> sortedEdges = new TreeSet<>(Comparator.comparing((Edge e) -> e.source.getSortKey())
                .thenComparing(e -> e.target.getSortKey()).thenComparingInt(e -> e.getPredicateOrder()));
        sortedEdges.addAll(graph.getEdges());

        for (Edge e : sortedEdges) {
            Message.ECHO.print(e.getDisplayString());
        }
    }
}
