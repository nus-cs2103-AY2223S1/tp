package seedu.rc4hdb.logic.commands.modelcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

/**
 * Lists the residents in the database.
 */
public class ListCommand extends ColumnManipulatorCommand {
    public static final String LIST_MESSAGE_SUCCESS = "Successfully listed all residents. ";
    public static final String LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN = LIST_MESSAGE_SUCCESS
            + "Only specified columns are displayed.\n"
            + "Use list to display all residents and columns. ";
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_PRESENT_TENSE = COMMAND_WORD;
    public static final String INCLUDE_SPECIFIER = "/i";
    public static final String EXCLUDE_SPECIFIER = "/e";

    /**
     * Constructor for a ListCommand instance.
     */
    public ListCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>());
    }

    /**
     * Constructor for a ListCommand instance.
     * @param fieldsToShow The list of fields to set to visible in the UI table
     * @param fieldsToHide The list of fields to set to not visible in the UI table
     */
    public ListCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);

        requireAtLeastOneVisibleColumn(fieldsToShow);

        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        if (fieldsToShow.equals(ColumnManipulatorCommand.ALL_FIELDS)) {
            return new CommandResult(LIST_MESSAGE_SUCCESS);
        } else {
            return new CommandResult(LIST_MESSAGE_SUCCESS_ONLY_SPECIFIED_COLUMNS_SHOWN);
        }
    }
}
