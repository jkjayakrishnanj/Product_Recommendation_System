package edu.kit.kastel.commands;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Edge;
import edu.kit.kastel.model.Graph;
import edu.kit.kastel.ui.EdgeParser;

/**
 * Command to remove an edge.
 *
 * @author uupyx
 */
public class RemoveCommand implements Command {

    private final Graph graph;
    private final String edgeSpecification;

    /**
     * Constructs a RemoveCommand.
     *
     * @param graph the graph.
     * @param edgeSpecification the edge specification.
     */
    public RemoveCommand(Graph graph, String edgeSpecification) {
        this.graph = graph;
        this.edgeSpecification = edgeSpecification;
    }

    /**
     * Executes the remove command.
     */
    @Override
    public void execute() {
        Edge edge = EdgeParser.parse(edgeSpecification);
        if (edge == null) {
            Message.INVALID_SYNTAX_REMOVE.error();
            return;
        }
        if (!graph.removeEdge(edge)) {
            Message.EDGE_NOT_FOUND.error();
        }
    }
}
