package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTOR;

import seedu.address.logic.commands.CommandResult.CommandType;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTutorCommand extends Command {

    public static final String COMMAND_WORD = "list_t";

    // the full string that calls list tutor which is used by UI
    // please update after changing list_t to list tutor/list t
    // by replacing the below list_s with either list tutor or list t
    public static final String COMMAND_LIST_TUTOR_STRING = "list_t";

    public static final String MESSAGE_SUCCESS = "Listed all tutors";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTOR);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.LIST);
    }
}
