package soconnect.logic.commands.todo;

import soconnect.logic.commands.Command;

/**
 * Represents a {@code Todo} command with hidden internal logic and the ability to be executed.
 */
public abstract class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
}
