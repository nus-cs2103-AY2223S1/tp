package bookface.logic.commands.list;

import bookface.logic.commands.Command;

/**
 * Lists all users in the user list to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, "Lists "
            + "users or books", COMMAND_WORD + " users");

    public static final String MESSAGE_SUCCESS = "Listed all users";

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @param commandDescription the description of the command
     * @param commandExample the example usage of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName, String commandDescription, String commandExample) {
        return Command.generateMessage(COMMAND_WORD + " " + commandName, "List all "
                + commandDescription, COMMAND_WORD + " " + commandExample);
    }
}
