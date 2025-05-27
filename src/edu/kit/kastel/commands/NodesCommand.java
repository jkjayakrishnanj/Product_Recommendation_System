package edu.kit.kastel.commands;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Graph;
import edu.kit.kastel.model.Node;

/**
 * Command to display all nodes.
 *
 * @author uupyx
 */
public class NodesCommand implements Command {

    private static final String SPACE = " ";

    private final Graph graph;

    /**
     * Constructs a NodesCommand.
     *
     * @param graph the graph.
     */
    public NodesCommand(Graph graph) {
        this.graph = graph;
    }

    /**
     * Executes the nodes command.
     */
    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        for (Node n : graph.getNodes()) {
            sb.append(n.getDisplayName()).append(SPACE);
        }

        Message.ECHO.print(sb.toString().trim());
    }
}
