package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.core.Messages.MESSAGE_COMPLETED_PROJECT;
import static jeryl.fyp.commons.core.Messages.MESSAGE_DUPLICATE_DEADLINE;
import static jeryl.fyp.commons.util.CollectionUtil.requireAllNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_DEADLINE_DATETIME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_DEADLINE_NAME;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Adds a deadline to a student.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "add -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to a student.\n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT_ID "
            + PREFIX_DEADLINE_NAME + "DEADLINE_NAME "
            + PREFIX_DEADLINE_DATETIME + "DEADLINE_DATETIME\n"
            + "(STUDENT_ID should be in format  \"A\" + (7 digits) + (1 uppercase letter), e.g. A0123456G)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A0123456G "
            + PREFIX_DEADLINE_NAME + "Midterm thesis report "
            + PREFIX_DEADLINE_DATETIME + "13-02-2022 11:11 ";

    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";

    private final Deadline toAdd;
    private StudentId studentId;

    /**
     * Creates an AddDeadlineCommand to add the specified {@code Deadline} to the
     * deadline list of specified {@code Student}
     */
    public AddDeadlineCommand(Deadline deadline, StudentId studentId) {
        requireAllNonNull(deadline, studentId);
        toAdd = deadline;
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Student student = model.getStudentByStudentId(studentId);

        // Can only add deadlines for incomplete projects.
        if (student.getProjectStatus().equals(new ProjectStatus("DONE"))) {
            throw new CommandException(MESSAGE_COMPLETED_PROJECT);
        }
        if (model.hasDeadline(student, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEADLINE);
        }

        model.addDeadline(student, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeadlineCommand // instanceof handles nulls
                && toAdd.equals(((AddDeadlineCommand) other).toAdd)
                && studentId.equals(((AddDeadlineCommand) other).studentId));
    }

}
