package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUITIONCLASS;

import seedu.address.logic.commands.CommandResult.CommandType;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTuitionClassCommand extends Command {

    public static final String COMMAND_WORD = "list_c";

    // the full string that calls list class which is used by UI
    // please update after changing list_c to list class/list c
    // by replacing the below list_c with either list class or list c
    public static final String COMMAND_LIST_CLASS_STRING = "list_c";

    public static final String MESSAGE_SUCCESS = "Listed all tuition classes";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        model.updateFilteredTuitionClassList(PREDICATE_SHOW_ALL_TUITIONCLASS);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.LIST);
    }
}
