package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_DEADLINE_RANK;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Deletes a deadline identified using it's unique StudentId and the rank of deadline.
 */
public class DeleteDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "delete -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the deadline identified by the student ID & rank provided.\n"
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT_ID "
            + PREFIX_DEADLINE_RANK + "DEADLINE_RANK\n"
            + "(STUDENT_ID must be a valid student ID that is already in the FYP manager)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A0123456G "
            + PREFIX_DEADLINE_RANK + "1";

    public static final String MESSAGE_DELETE_DEADLINE_SUCCESS = "Deleted Deadline: %1$s";

    private final StudentId studentId;
    private final Integer rank;

    /**
     * Contructs a DeleteDeadlineCommand with specified student, and the rank of task to be deleted.
     * @param studentId The student's ID.
     * @param number Rank of task to be deleted.
     */
    public DeleteDeadlineCommand(StudentId studentId, Integer number) {
        this.studentId = studentId;
        this.rank = number;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Student student = model.getStudentByStudentId(studentId);
        if (student == null) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        if ((rank < 1) || (rank > student.getDeadlineList().size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_DEADLINE_RANK);
        }
        Deadline deadlineToDelete = student.getDeadlineList().getDeadlineByRank(rank - 1);
        model.deleteDeadline(student, deadlineToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DEADLINE_SUCCESS, deadlineToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDeadlineCommand // instanceof handles nulls
                && studentId.equals(((DeleteDeadlineCommand) other).studentId) // state check
                && rank.equals(((DeleteDeadlineCommand) other).rank));
    }

}
