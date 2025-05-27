package edu.kit.kastel.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.kit.kastel.Message;
import edu.kit.kastel.model.Edge;
import edu.kit.kastel.model.Graph;
import edu.kit.kastel.ui.EdgeParser;

/**
 * Command to load a database.
 *
 * @author uupyx
 */
public class LoadDatabaseCommand implements Command {

    private static final String SPACE_REGEX = "\\s+";
    private static final int MINIMUM_PARTS_LENGTH = 3;
    private static final int PATH_INDEX = 2;

    private final Graph graph;
    private final String command;

    /**
     * Constructs a LoadDatabaseCommand.
     *
     * @param graph the graph.
     * @param command the command string.
     */
    public LoadDatabaseCommand(Graph graph, String command) {
        this.graph = graph;
        this.command = command;
    }

    /**
     * Executes the load database command.
     */
    @Override
    public void execute() {
        String[] parts = command.split(SPACE_REGEX, MINIMUM_PARTS_LENGTH);
        if (parts.length < MINIMUM_PARTS_LENGTH) {
            Message.FILE_PATH_REQUIRED.error();
            return;
        }

        String path = parts[PATH_INDEX];
        Path file = Paths.get(path);
        if (!Files.exists(file) || Files.isDirectory(file)) {
            Message.FILE_DOES_NOT_EXIST.error();
            return;
        }

        graph.reset();

        try {
            Message.ECHO.print(Files.readString(file));
        } catch (IOException e) {
            Message.ERROR.error(e.getMessage());
            return;
        }

        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                Edge edge = EdgeParser.parse(line);
                if (edge == null) {
                    Message.INVALID_FORMAT.error(line);
                    return;
                }

                if (!graph.addEdge(edge)) {
                    Message.INVALID_RELATIONSHIP.error();
                    return;
                }

            }
        } catch (IOException e) {
            Message.ERROR.error(e.getMessage());
            return;
        }

        graph.setLoaded(true);
    }
}
