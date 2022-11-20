package bookface.logic.commands.delete;

import bookface.logic.commands.Command;

/**
 * Deletes a user identified using it's displayed index from the user list.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private static final String DELETE_DESCRIPTION = "Deletes the %1$s identified "
            + "by the index number used in the displayed %1$s list";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, String.format(DELETE_DESCRIPTION,
            "user or book"), COMMAND_WORD + " user 2");

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName) {
        return Command.generateMessage(COMMAND_WORD + " " + commandName,
                String.format(DELETE_DESCRIPTION, commandName), "INDEX (must be a positive integer)",
                COMMAND_WORD + " " + commandName + " 1");
    }
}
