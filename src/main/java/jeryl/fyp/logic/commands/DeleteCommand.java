package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Deletes a student identified using it's unique StudentId.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by it's unique student id.\n"
            + "Parameters: STUDENT_ID (must be a valid student id that is already in the FYP manager)\n"
            + "Example: " + COMMAND_WORD + " A0123456G";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final StudentId studentId;

    public DeleteCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        Index targetIndex = model.getIndexByStudentId(studentId);
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && studentId.equals(((DeleteCommand) other).studentId)); // state check
    }
}
