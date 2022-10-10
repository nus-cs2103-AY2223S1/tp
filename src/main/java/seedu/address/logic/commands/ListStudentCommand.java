package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.ListType;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENT;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list_s";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentListType(ListType.STUDENT_LIST);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENT);
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
