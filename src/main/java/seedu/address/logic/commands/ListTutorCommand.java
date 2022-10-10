package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.LIST_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTOR;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTutorCommand extends Command {

    public static final String COMMAND_WORD = "listT";

    public static final String MESSAGE_SUCCESS = "Listed all tutors";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentListType(LIST_TYPE.TUTOR_LIST);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTOR);
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
