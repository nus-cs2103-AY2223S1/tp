package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.classify.model.Model;
import seedu.classify.model.StudentRecord;

/**
 * Clears all student records from local storage.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All student records have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudentRecord(new StudentRecord());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
