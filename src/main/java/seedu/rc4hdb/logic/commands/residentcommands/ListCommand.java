package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;

/**
 * Lists the residents in the database.
 */
public class ListCommand extends ColumnManipulatorCommand {

    public static final String LIST_MESSAGE_SUCCESS = "Successfully listed all residents. ";

    public static final String COMMAND_WORD = "list";

    private static final Logger logger = Logger.getLogger("ListCommand");

    /**
     * Constructor for a ListCommand instance.
     */
    public ListCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Going to start execution.");

        // Some intentional, defensive checks for field validity and the minimum number of columns are performed here

        requireAllFieldsValid(fieldsToShow);
        requireAllFieldsValid(fieldsToHide);
        logger.log(Level.INFO, "Fields entered are valid.");

        requireAtLeastOneVisibleColumn(fieldsToShow);

        model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
        model.setVisibleFields(fieldsToShow);
        model.setHiddenFields(fieldsToHide);

        logger.log(Level.INFO, "Execution completed, returning CommandResult for list command.");
        return new CommandResult(LIST_MESSAGE_SUCCESS);
    }
}
