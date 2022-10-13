package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUITIONCLASS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTuitionClassCommand extends Command {

    public static final String COMMAND_WORD = "list_c";

    public static final String MESSAGE_SUCCESS = "Listed all tuition classes";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        model.updateFilteredTuitionClassList(PREDICATE_SHOW_ALL_TUITIONCLASS);
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
