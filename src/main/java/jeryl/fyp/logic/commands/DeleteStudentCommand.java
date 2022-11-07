package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;

import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Deletes a student identified using it's unique StudentId.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "delete -s";

    public static final String ALTERNATIVE_COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by it's unique student ID\n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT_ID\n"
            + "(STUDENT_ID must be a valid student ID that is already in the FYP manager)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A0123456G";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final StudentId studentId;

    public DeleteStudentCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        Index targetIndex = model.getIndexByStudentId(studentId);
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && studentId.equals(((DeleteStudentCommand) other).studentId)); // state check
    }

}
