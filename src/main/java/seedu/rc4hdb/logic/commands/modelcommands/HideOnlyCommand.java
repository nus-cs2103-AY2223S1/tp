package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

public class HideOnlyCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "hideonly";
    public static final String COMMAND_PAST_TENSE = "hidden only";
    public static final String COMMAND_PRESENT_TENSE = "hide";

    public HideOnlyCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireValidList(model, fieldsToHide);

        List<String> updatedFieldsToHide = getUnionOfFieldsToHideAndAlreadyHiddenFields(model, fieldsToHide);
        List<String> updatedFieldsToShow = generateComplementListFrom(updatedFieldsToHide);

        requireAtLeastOneVisibleColumn(updatedFieldsToShow);

        model.setVisibleFields(updatedFieldsToShow);
        model.setHiddenFields(updatedFieldsToHide);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT_RESTORE_FULL_VIEW, COMMAND_PAST_TENSE));
    }
}
