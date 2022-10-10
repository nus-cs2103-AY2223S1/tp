package jarvis.logic.commands;

import static java.util.Objects.requireNonNull;

import jarvis.model.Model;
import jarvis.model.StudentBook;
import jarvis.model.TaskBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Both student list and task list have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudentBook(new StudentBook());
        model.setTaskBook(new TaskBook());
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
