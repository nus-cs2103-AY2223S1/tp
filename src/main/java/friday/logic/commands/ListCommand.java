package friday.logic.commands;

import static friday.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static java.util.Objects.requireNonNull;

import friday.model.Model;

/**
 * Lists all students in FRIDAY to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
