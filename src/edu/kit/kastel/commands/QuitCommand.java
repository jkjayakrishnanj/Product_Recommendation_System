package edu.kit.kastel.commands;

/**
 * Command to signal that the application should quit. The main application loop should check this status and terminate accordingly.
 *
 * @author uupyx
 */
public class QuitCommand implements Command {
    /**
     * Executes the quit command by setting a termination flag. The main application loop must detect this and stop execution.
     */
    @Override
    public void execute() {
        Thread.currentThread().interrupt();
    }
}
