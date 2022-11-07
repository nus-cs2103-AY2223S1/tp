package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

/**
 * Hides the columns in the current table.
 */
public class HideOnlyCommand extends ColumnManipulatorCommand {

    public static final String COMMAND_WORD = "hideonly";
    public static final String COMMAND_PAST_TENSE = "hidden only";
    public static final String COMMAND_PRESENT_TENSE = "hide";
    private static final Logger logger = Logger.getLogger("HideOnlyCommand");

    /**
     * Constructor for a HideOnlyCommand instance.
     * @param fieldsToShow The list of fields to set to visible in the UI table
     * @param fieldsToHide The list of fields to set to not visible in the UI table
     */
    public HideOnlyCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Going to start execution.");

        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);
        logger.log(Level.INFO, "Fields entered are valid.");

        // Check if the fields to further hide correspond to existing columns in the current table
        requireValidSubsetOfAlreadyVisibleFields(model, fieldsToHide);
        logger.log(Level.INFO, "Fields to hide are a valid subset of the current table columns.");

        // Combine the already hidden fields and the fields to further hide
        List<String> updatedFieldsToHide = getUnionOfFieldsToHideAndAlreadyHiddenFields(model, fieldsToHide);
        List<String> updatedFieldsToShow = generateComplementListFrom(updatedFieldsToHide);

        requireAtLeastOneVisibleColumn(updatedFieldsToShow);

        logger.log(Level.INFO, "Updating model with the fields to hide.");
        model.setVisibleFields(updatedFieldsToShow);
        model.setHiddenFields(updatedFieldsToHide);

        logger.log(Level.INFO, "Execution completed, returning CommandResult");
        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE));
    }
}
