package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

public class ResetCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "reset";
    public static final String COMMAND_PAST_TENSE = COMMAND_WORD;

    public ResetCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireValidSubsetOfAlreadyVisibleFields(model, fieldsToHide);

        requireAtLeastOneVisibleColumn(fieldsToShow);

        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, COMMAND_PAST_TENSE));
    }
}
