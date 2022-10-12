package bookface.logic.commands.add;

import bookface.logic.commands.Command;

/**
 * Adds a person to BookFace.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    //todo so we want a way to configure the help message for all levels of commands as well
    // so this variable needs to be used
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an entity to BookFace. "
            + "Suitable subcommands: \n"
            + "Example: " + COMMAND_WORD
            + " user "
            + "...";
}
