package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static java.util.Objects.requireNonNull;

import jarvis.model.Model;
import jarvis.ui.DisplayedList;

/**
 * Lists all students in the student book to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "lists";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS, DisplayedList.EXP_STUDENT_LIST);
    }
}
