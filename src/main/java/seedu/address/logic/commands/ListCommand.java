package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all modules and persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_TYPE = "list";
    public static final String COMMAND_WORD = COMMAND_TYPE;

    public static final String MESSAGE_SUCCESS_PERSON = "Listed all persons! ";
    public static final String MESSAGE_SUCCESS_MODULE = "Listed all modules! ";
    public static final String MESSAGE_NO_CONTACTS_IN_LIST = "You have not added any contacts yet! ";
    public static final String MESSAGE_NO_MODULES_IN_LIST = "You have not added any module yet! ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        int num_Person = model.getFilteredPersonList().size();
        int num_Module = model.getFilteredModuleList().size();
        String personResult = num_Person == 0 ? MESSAGE_NO_CONTACTS_IN_LIST : MESSAGE_SUCCESS_PERSON;
        String moduleResult = num_Module == 0 ? MESSAGE_NO_MODULES_IN_LIST : MESSAGE_SUCCESS_MODULE;
        return new CommandResult(personResult + moduleResult);
    }
}
