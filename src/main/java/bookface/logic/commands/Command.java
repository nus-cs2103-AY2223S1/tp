package bookface.logic.commands;

import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    public static final String MESSAGE_USAGE = "Get the program usage instructions by typing help!";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @param commandDescription the description of the command
     * @param commandExample the example usage of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName, String commandDescription, String commandExample) {
        return commandName + ": " + commandDescription + ".\nExample: " + commandExample;
    }

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @param commandDescription the description of the command
     * @param commandArguments the arrguments of the command
     * @param commandExample the example usage of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName, String commandDescription, String commandArguments,
                                         String commandExample) {
        return commandName + ": " + commandDescription + ".\nParameters: " + commandArguments
                + "\nExample: " + commandExample;
    }
}
