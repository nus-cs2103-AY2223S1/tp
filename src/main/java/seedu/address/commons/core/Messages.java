package seedu.address.commons.core;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditModuleCommand;
/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX =
            "No such person exists in the current list. Please provide a valid index.";
    public static final String MESSAGE_INVALID_MODULE_DISPLAYED_INDEX =
            "No such module exists in the current list. Please provide a valid index.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person(s) listed!";
    public static final String MESSAGE_MODULES_LISTED_OVERVIEW = "%1$d module(s) listed!";

    /* Error messages for "almost" wrong commands*/
    public static final String MESSAGE_INVALID_ADD_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", AddModuleCommand.COMMAND_WORD, AddCommand.COMMAND_WORD);
    public static final String MESSAGE_INVALID_DELETE_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", DeleteModuleCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD);
    public static final String MESSAGE_INVALID_EDIT_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", EditModuleCommand.COMMAND_WORD, EditCommand.COMMAND_WORD);

}
