package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

public class ShowOnlyCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "showonly";
    public static final String COMMAND_VERBS = "shown only";

    public ShowOnlyCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>()); // very sus
    }

    public ShowOnlyCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireValidList(model, fieldsToShow);
        requireAtLeastOneVisibleColumn(fieldsToShow);

        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, getCommandVerbs()));
    }

    @Override
    public String getCommandVerbs() {
        return COMMAND_VERBS;
    }
}
