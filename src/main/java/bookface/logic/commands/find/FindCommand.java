package bookface.logic.commands.find;

import bookface.logic.commands.Command;

/**
 * Finds and lists all users in user list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private static final String FIND_DESCRIPTION = "Finds a %s which contain any of the specified keywords "
            + "(case-insensitive) and displays them as a "
            + "list with index numbers";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, String.format(FIND_DESCRIPTION,
                    "user or book"), COMMAND_WORD + " user john");

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @param commandExample the example usage of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName, String commandExample) {
        return Command.generateMessage(COMMAND_WORD + " " + commandName,
                String.format(FIND_DESCRIPTION, commandName), "KEYWORD [KEYWORD]...",
                COMMAND_WORD + " " + commandExample);
    }
}
