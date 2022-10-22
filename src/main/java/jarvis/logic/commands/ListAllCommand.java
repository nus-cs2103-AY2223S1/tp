package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static jarvis.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static java.util.Objects.requireNonNull;

import jarvis.model.Model;
import jarvis.ui.DisplayedList;

/**
 * Lists all students in the student book to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "listall";

    public static final String MESSAGE_SUCCESS = "Listed all students, tasks and lessons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, DisplayedList.DEFAULT_LIST);
    }
}
