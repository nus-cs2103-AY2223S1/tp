package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

/**
 * Sets only certain columns in the current UI table to be visible.
 */
public class ShowOnlyCommand extends ColumnManipulatorCommand {

    public static final String COMMAND_WORD = "showonly";
    public static final String COMMAND_PAST_TENSE = "shown only";
    public static final String COMMAND_PRESENT_TENSE = "show";
    private static final Logger logger = Logger.getLogger("ShowOnlyCommand");

    /**
     * Constructor for a ShowOnlyCommand instance.
     * @param fieldsToShow The list of fields to be set to visible in the UI table
     * @param fieldsToHide The list of fields to be set to not visible in the UI table
     */
    public ShowOnlyCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Going to start execution.");

        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);
        logger.log(Level.INFO, "Fields entered are valid.");

        // Check if the fields to show correspond to existing columns in the current table
        requireValidSubsetOfAlreadyVisibleFields(model, fieldsToShow);
        logger.log(Level.INFO, "Fields to show are a valid subset of the current table columns.");

        requireAtLeastOneVisibleColumn(fieldsToShow);

        logger.log(Level.INFO, "Updating model with the fields to show.");
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        logger.log(Level.INFO, "Execution completed, returning CommandResult.");
        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE));
    }
}
