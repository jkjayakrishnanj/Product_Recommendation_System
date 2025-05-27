package edu.kit.kastel;

import java.util.Scanner;

import edu.kit.kastel.commands.Command;
import edu.kit.kastel.commands.CommandParser;
import edu.kit.kastel.model.Graph;

/**
 * Main class for the product recommendation system.
 *
 * <p>
 * The main loop checks the interrupt status of the current thread. The {@code QuitCommand} signals termination by interrupting the thread.
 * </p>
 *
 * <p>
 * According to the current Checkstyle rules, we are not allowed to catch RuntimeException directly. If you need to handle specific runtime
 * errors, catch them individually or let them propagate.
 * </p>
 *
 * @author uupyx
 */
public final class Recommender {

    private final Graph graph;

    /**
     * Constructs a new Recommender.
     */
    public Recommender() {
        this.graph = new Graph();
    }

    /**
     * Main method.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Recommender app = new Recommender();
        app.run();
    }

    /**
     * Runs the command loop. The loop terminates when the current thread is interrupted.
     */
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!Thread.currentThread().isInterrupted()) {
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }

                Command command = CommandParser.parse(input, graph);
                if (command == null) {
                    Message.UNKNOWN_COMMAND.error();
                } else {
                    command.execute();
                }
            }
        }
    }
}
