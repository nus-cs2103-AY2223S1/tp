package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Clears all the records of a patient.
 */
public class ClearRecordCommand extends Command {

    public static final String COMMAND_WORD = "rclear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the records of the currently specified record list.\n";

    public static final String MESSAGE_SUCCESS = "Patient's record list has been cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_RECORD_COMMAND_PREREQUISITE);
        }

        model.clearRecords();

        return new CommandResult(MESSAGE_SUCCESS,
                false, false, true);
    }
}
