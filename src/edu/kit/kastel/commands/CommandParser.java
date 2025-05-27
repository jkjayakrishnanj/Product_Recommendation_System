package edu.kit.kastel.commands;

import edu.kit.kastel.model.Graph;

/**
 * Utility-class parsing user input in command objects.
 *
 * @author uupyx
 */
public final class CommandParser {

    private static final CommandFactory[] FACTORIES = new CommandFactory[] { new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.startsWith("load database");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new LoadDatabaseCommand(graph, input);
        }

        @Override
        public boolean requiresGraph() {
            return false;
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.equals("quit");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new QuitCommand();
        }

        @Override
        public boolean requiresGraph() {
            return false;
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.startsWith("add ");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new AddCommand(graph, input.substring(4).trim());
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.startsWith("remove ");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new RemoveCommand(graph, input.substring(7).trim());
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.equals("nodes");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new NodesCommand(graph);
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.equals("edges");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new EdgesCommand(graph);
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.startsWith("recommend");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new RecommendCommand(graph, input.substring(9).trim());
        }
    }, new CommandFactory() {
        @Override
        public boolean matches(String input) {
            return input.equals("export");
        }

        @Override
        public Command create(String input, Graph graph) {
            return new ExportCommand(graph);
        }
    } };

    /**
     * Private constructor, prevents initialisation.
     */
    private CommandParser() {

    }

    private interface CommandFactory {
        boolean matches(String input);

        Command create(String input, Graph graph);

        default boolean requiresGraph() {
            return true;
        }
    }

    /**
     * Parses the input string and returns the corresponding comman-object.
     *
     * @param input the user input
     * @param graph the graph instance
     * @return the command object or null, if the input is invalid
     */
    public static Command parse(String input, Graph graph) {
        for (CommandFactory factory : FACTORIES) {
            if (factory.matches(input)) {
                if (factory.requiresGraph() && !graph.isLoaded()) {
                    return null;
                }
                return factory.create(input, graph);
            }
        }

        return null;
    }

}
