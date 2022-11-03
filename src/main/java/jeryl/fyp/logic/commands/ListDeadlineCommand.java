package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * Lists all Deadlines under one specific students in the FYP manager to the user.
 */
public class ListDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "list -d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all the Deadlines under a specific student in the FYP manager."
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT_ID\n"
            + "(STUDENT_ID must be a valid student id that is already in the FYP manager)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A0123456G";

    public static final String MESSAGE_LIST_DEADLINE_SUCCESS = "Listed all Deadlines under Student %1$s";

    private final StudentId studentId;

    public ListDeadlineCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        Index targetIndex = model.getIndexByStudentId(studentId);
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }

        Student studentToListDeadline = lastShownList.get(targetIndex.getZeroBased());
        model.listDeadlineUnderStudent(studentToListDeadline);
        return new CommandResult(String.format(MESSAGE_LIST_DEADLINE_SUCCESS, studentToListDeadline));
    }

}
