package jarvis.logic.commands.student;

import static java.util.Objects.requireNonNull;

import jarvis.logic.commands.Command;
import jarvis.logic.commands.CommandResult;
import jarvis.model.StudentBook;
import jarvis.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudentBook(new StudentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
