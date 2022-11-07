package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_STATUS;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

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
            + "by the student ID given in the command. "
            + "Existing statuses will be updated by the input.\n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT_ID "
            + PREFIX_PROJECT_STATUS + "STATUS\n"
            + "(STUDENT_ID must be a valid student ID that is already in the FYP manager)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A0123456G "
            + PREFIX_PROJECT_STATUS + "DONE";

    public static final String MESSAGE_MARK_PROJECT_STATUS_SUCCESS = "Marked ProjectStatus to Student: %1$s";

    private final StudentId studentId;
    private final ProjectStatus projectStatus;

    /**
     * @param studentId of the student doing a particular FYP project
     * @param projectStatus of the FYP project
     */
    public MarkCommand(StudentId studentId, ProjectStatus projectStatus) {
        requireAllNonNull(studentId, projectStatus);
        this.studentId = studentId;
        this.projectStatus = projectStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Student oldStudent = model.getStudentByStudentId(studentId);

        Student editedStudent = new Student(oldStudent.getStudentName(), oldStudent.getStudentId(),
                oldStudent.getEmail(), oldStudent.getProjectName(), projectStatus,
                oldStudent.getDeadlineList(), oldStudent.getTags());

        model.setStudent(oldStudent, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));

    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        return String.format(MESSAGE_MARK_PROJECT_STATUS_SUCCESS, studentToEdit);
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
                && projectStatus.equals(e.projectStatus);
    }

}
