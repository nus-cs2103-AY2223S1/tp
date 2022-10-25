package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

public class ListCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_VERBS = "listed only";
    public static final String INCLUDE_SPECIFIER = "/i";
    public static final String EXCLUDE_SPECIFIER = "/e";

    public ListCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>()); // very sus
    }
    public ListCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireAtLeastOneVisibleColumn(this.fieldsToShow);

        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, getCommandVerbs()));
    }

    @Override
    public String getCommandVerbs() {
        return COMMAND_VERBS;
    }
}
