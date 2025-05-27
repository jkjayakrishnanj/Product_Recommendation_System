package edu.kit.kastel.commands;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Graph;
import edu.kit.kastel.model.Product;
import edu.kit.kastel.model.Term;
import edu.kit.kastel.ui.ParseException;
import edu.kit.kastel.ui.RecommendationParser;

/**
 * Command to evaluate recommendation expressions.
 *
 * @author uupyx
 */
public class RecommendCommand implements Command {

    private static final String SPACE = " ";

    private final Graph graph;
    private final String expression;

    /**
     * Constructs a RecommendCommand.
     *
     * @param graph the graph.
     * @param expression the recommendation expression.
     */
    public RecommendCommand(Graph graph, String expression) {
        this.graph = graph;
        this.expression = expression;
    }

    /**
     * Executes the recommend command.
     */
    @Override
    public void execute() {
        try {
            RecommendationParser parser = new RecommendationParser(expression);
            Term term = parser.parse();
            Set<Product> result = term.evaluate(graph);
            TreeSet<Product> sorted = new TreeSet<>(Comparator.comparing(p -> p.name.toLowerCase()));
            sorted.addAll(result);
            StringBuilder sb = new StringBuilder();
            for (Product p : sorted) {
                sb.append(p.getDisplayName()).append(SPACE);
            }
            Message.ECHO.print(sb.toString().trim());
        } catch (ParseException pe) {
            Message.ERROR.error(pe.getMessage());
        }
    }
}
