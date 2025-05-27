package edu.kit.kastel.commands;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Edge;
import edu.kit.kastel.model.Graph;
import edu.kit.kastel.ui.EdgeParser;

/**
 * Command to add an edge to the graph.
 *
 * @author uupyx
 */
public final class AddCommand implements Command {

    private final Graph graph;
    private final String edgeSpecification;

    /**
     * Constructs an AddCommand.
     *
     * @param graph the graph to add the edge to
     * @param edgeSpecification the edge specification
     */
    public AddCommand(Graph graph, String edgeSpecification) {
        this.graph = graph;
        this.edgeSpecification = edgeSpecification;
    }

    /**
     * Executes the add command.
     */
    @Override
    public void execute() {
        Edge edge = EdgeParser.parse(edgeSpecification);
        if (edge != null) {
            if (!graph.addEdge(edge)) {
                Message.INVALID_EDGE.error();
            }
        } else {
            Message.INVALID_SYNTAX.error();
        }
    }
}
