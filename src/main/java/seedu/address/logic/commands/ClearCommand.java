package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.TeachersPet;
import seedu.address.storage.ClassStorage;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyTeachersPet teachersPet = new TeachersPet();
        model.setTeachersPet(teachersPet);
        ClassStorage classStorage = new ClassStorage(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
