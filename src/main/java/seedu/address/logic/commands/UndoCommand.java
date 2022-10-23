package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Select the given appointment and filter appointment list by given appointment.
 */
public class UndoCommand extends Command {
    public static final CommandWord COMMAND_WORD =
            new CommandWord("undo");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the last command\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undo successful";

    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

