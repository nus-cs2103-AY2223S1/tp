package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

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

        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);

        requireValidSubsetOfAlreadyVisibleFields(model, fieldsToHide);

        List<String> updatedFieldsToHide = getUnionOfFieldsToHideAndAlreadyHiddenFields(model, fieldsToHide);
        List<String> updatedFieldsToShow = generateComplementListFrom(updatedFieldsToHide);

        requireAtLeastOneVisibleColumn(updatedFieldsToShow);

        model.setVisibleFields(updatedFieldsToShow);
        model.setHiddenFields(updatedFieldsToHide);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE));
    }
}
