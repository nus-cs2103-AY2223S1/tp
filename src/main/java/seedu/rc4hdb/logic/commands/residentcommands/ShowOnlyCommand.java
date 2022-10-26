package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

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

        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);

        requireValidSubsetOfAlreadyVisibleFields(model, fieldsToShow);
        requireAtLeastOneVisibleColumn(fieldsToShow);

        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE));
    }
}
