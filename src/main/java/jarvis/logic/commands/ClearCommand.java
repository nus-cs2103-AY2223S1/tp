package jarvis.logic.commands;

import static java.util.Objects.requireNonNull;

import jarvis.model.LessonBook;
import jarvis.model.Model;
import jarvis.model.StudentBook;
import jarvis.model.TaskBook;

/**
 * Clears the student list, task list and lesson list in JARVIS.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Student list, task list and lesson list have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudentBook(new StudentBook());
        model.setTaskBook(new TaskBook());
        model.setLessonBook(new LessonBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
