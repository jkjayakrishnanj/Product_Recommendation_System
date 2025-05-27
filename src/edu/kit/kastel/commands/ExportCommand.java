package edu.kit.kastel.commands;

import java.util.Comparator;
import java.util.TreeSet;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Category;
import edu.kit.kastel.model.Edge;
import edu.kit.kastel.model.Graph;

/**
 * Command to export the graph in DOT format.
 *
 * @author uupyx
 */
public class ExportCommand implements Command {

    private final Graph graph;

    /**
     * Constructs an ExportCommand.
     *
     * @param graph the graph.
     */
    public ExportCommand(Graph graph) {
        this.graph = graph;
    }

    /**
     * Executes the export command.
     */
    @Override
    public void execute() {
        Message.DIGRAPH_START.print();

        TreeSet<Edge> sortedEdges = new TreeSet<>(Comparator.comparing((Edge e) -> e.source.getSortKey())
                .thenComparing(e -> e.target.getSortKey()).thenComparing(e -> e.getPredicateOrder()));
        sortedEdges.addAll(graph.getEdges());

        for (Edge e : sortedEdges) {
            Message.ECHO.print(e.getDotString());
        }
        for (Category c : graph.getCategories()) {
            Message.DIGRAPH_SHAPE.print(c.name.toLowerCase());
        }

        Message.DIGRAPH_END.print();
    }
}
