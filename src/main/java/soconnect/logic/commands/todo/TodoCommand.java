package soconnect.logic.commands.todo;

import soconnect.logic.commands.Command;

/**
 * Represents a {@code Todo} command with hidden internal logic and the ability to be executed.
 */
public abstract class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Use a subcommand to access Todo features.\n"
        + "Subcommands: "
        + "add, "
        + "delete, "
        + "edit, "
        + "clear, "
        + "show\n"
        + "Example: " + COMMAND_WORD + " add";
}
