package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class StudentDeleteCommand extends Command {

    public static final String COMMAND_WORD = "student delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number(s) (separated by whitespace)"
            + "used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer and NOT TOO BIG)\n"
            + "Example: " + COMMAND_WORD + " 1 2 4";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";

    private final Index[] targetIndexes;

    public StudentDeleteCommand(Index[] targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        ArrayList<Student> studentsToDelete = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
            studentsToDelete.add(studentToDelete);
        }

        for (Student student : studentsToDelete) {
            model.deleteStudent(student);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, studentsToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentDeleteCommand // instanceof handles nulls
                && Arrays.equals(this.targetIndexes, ((StudentDeleteCommand) other).targetIndexes)); // state check
    }
}
