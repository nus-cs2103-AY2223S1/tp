package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears all the records of a patient.
 */
public class ClearRecordCommand extends Command {

    public static final String COMMAND_WORD = "clearR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the records of the currently specified record list.\n";

    public static final String MESSAGE_SUCCESS = "Patient's Record List has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // todo: throw exception if listR has not been called.
        model.clearRecords();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
