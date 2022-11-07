package seedu.uninurse.logic.commands;

import seedu.uninurse.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_SUCCESS = "Exiting UniNurse Book as requested ...";
    public static final CommandType COMMAND_TYPE = CommandType.EXIT;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, COMMAND_TYPE);
    }
}
