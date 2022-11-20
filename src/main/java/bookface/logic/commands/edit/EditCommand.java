package bookface.logic.commands.edit;

import bookface.logic.commands.Command;

/**
 * Finds and lists all users in user list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private static final String EDIT_DESCRIPTION = "Edits the details of the %1$s identified "
            + "by the index number used in the displayed %1$s list. "
            + "Existing values will be overwritten by the input values";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, String.format(EDIT_DESCRIPTION,
            "user or book"), COMMAND_WORD + " user 1 n/John Doe");

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @param commandArguments the arguments of the command
     * @param commandExample the example usage of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName, String commandArguments, String commandExample) {
        return Command.generateMessage(COMMAND_WORD + " " + commandName,
                String.format(EDIT_DESCRIPTION, commandName), commandArguments,
                COMMAND_WORD + " " + commandExample);
    }
}
