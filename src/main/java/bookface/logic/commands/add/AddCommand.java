package bookface.logic.commands.add;

import bookface.logic.commands.Command;

/**
 * Adds a person to BookFace.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    private static final String ADD_DESCRIPTION = "Adds a %s to BookFace";

    public static final String MESSAGE_USAGE = Command.generateMessage(COMMAND_WORD, String.format(ADD_DESCRIPTION,
            "user or book"), COMMAND_WORD + " book t/The Deep Dive a/John Doe");

    /**
     * Generates an usage message.
     * @param commandName the name of the command
     * @param commandArguments the arguments of the command
     * @param commandExample the example usage of the command
     * @return The generated usage message
     */
    public static String generateMessage(String commandName, String commandArguments, String commandExample) {
        return Command.generateMessage(COMMAND_WORD + " " + commandName,
                String.format(ADD_DESCRIPTION, commandName), commandArguments,
                COMMAND_WORD + " " + commandExample);
    }
}
