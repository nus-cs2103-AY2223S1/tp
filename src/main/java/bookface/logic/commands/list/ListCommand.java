package bookface.logic.commands.list;

import bookface.logic.commands.Command;

/**
 * Lists all users in the user list to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, "Lists "
            + "users, books, loans or overdue books", COMMAND_WORD + " users");

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName) {
        return Command.generateMessage(COMMAND_WORD + " " + commandName, "List "
                + commandName, COMMAND_WORD + " " + commandName);
    }
}
