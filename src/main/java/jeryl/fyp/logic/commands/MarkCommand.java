package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STATUS;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;


/**
 * Updates the status of an existing FYP to "DONE", "IP" or "YTS"
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates status of the FYP project done"
            + "by the student ID given in the command "
            + "Existing statuses will be updated by the input.\n"
            + "Parameters: id/STUDENT_ID (should be in format  \"A\" + (7 digits) + (1 letter), e.g. A0123456G"
            + "s/STATUS\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_ID + "A0123456G "
            + PREFIX_STATUS + "DONE";

    public static final String MESSAGE_ADD_PROJECT_STATUS_SUCCESS = "Added ProjectStatus to Student: %1$s";
    public static final String MESSAGE_DELETE_PROJECT_STATUS_SUCCESS = "Removed ProjectStatus from Student: %1$s";

    private final StudentId studentId;
    private final ProjectStatus status;

    /**
     * @param studentId of the student doing a particular FYP project
     * @param status of the FYP project
     */
    public MarkCommand(StudentId studentId, ProjectStatus status) {
        requireAllNonNull(studentId, status);
        this.studentId = studentId;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Student oldStudent = model.getStudent(studentId);

        if (oldStudent == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_ID);
        }

        Student editedStudent = new Student(oldStudent.getName(), oldStudent.getStudentId(),
                oldStudent.getEmail(), oldStudent.getProjectName(), status, oldStudent.getTags());

        model.setStudent(oldStudent, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));

    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !status.projectStatus.isEmpty() ? MESSAGE_ADD_PROJECT_STATUS_SUCCESS
                : MESSAGE_DELETE_PROJECT_STATUS_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return studentId.equals(e.studentId)
                && status.equals(e.status);
    }
}
