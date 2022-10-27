package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

/**
 * Resets the visibilities for all columns in the UI table.
 */
public class ResetCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "reset";
    public static final String COMMAND_PAST_TENSE = COMMAND_WORD;
    private static final Logger logger = Logger.getLogger("ResetCommand");

    /**
     * Constructor for a ResetCommand instance.
     */
    public ResetCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Going to start execution.");

        // Defensively checks for invalid fields in both field lists
        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);
        requireAtLeastOneVisibleColumn(fieldsToShow);

        logger.log(Level.INFO, "Updating model with the fields to reset.");
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        logger.log(Level.INFO, "Execution completed, returning CommandResult.");
        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, COMMAND_PAST_TENSE));
    }
}
