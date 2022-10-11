package seedu.address.commons.core;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListModuleCommand;
/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_MODULE_DISPLAYED_INDEX = "The module index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    /* Error messages for "almost" wrong commands*/
    public static final String MESSAGE_INVALID_ADD_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", AddModuleCommand.COMMAND_WORD, AddCommand.COMMAND_WORD);
    public static final String MESSAGE_INVALID_DELETE_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", DeleteModuleCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD);
    public static final String MESSAGE_INVALID_LIST_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", ListModuleCommand.COMMAND_WORD, ListCommand.COMMAND_WORD);
    public static final String MESSAGE_INVALID_EDIT_COMMAND = MESSAGE_UNKNOWN_COMMAND
        + String.format("\nTry using %s and %s!", EditModuleCommand.COMMAND_WORD, EditCommand.COMMAND_WORD);

}
