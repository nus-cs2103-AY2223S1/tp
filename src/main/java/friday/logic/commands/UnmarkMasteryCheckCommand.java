package friday.logic.commands;

import java.util.List;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.student.Student;

/**
 * Unmarks the Mastery Check of an existing student in FRIDAY.
 */
public class UnmarkMasteryCheckCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the Mastery Check of the student identified by"
            + " the index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_SUCCESS = "Unmarked Mastery Check of Student: %s";
    public static final String MESSAGE_NOT_MARKED = "Can't unmark as %s's Mastery Check has not been marked as passed!";
    public static final String MESSAGE_EMPTY_MASTERYCHECK = " currently does not have any scheduled Mastery Check to be"
            + " unmarked.";

    private Index index;

    public UnmarkMasteryCheckCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToUnmark = lastShownList.get(index.getZeroBased());
        if (studentToUnmark.getMasteryCheck().isEmpty()) {
            throw new CommandException(studentToUnmark.getName() + MESSAGE_EMPTY_MASTERYCHECK);
        } else if (!studentToUnmark.getMasteryCheck().getIsPassed()) {
            throw new CommandException(String.format(MESSAGE_NOT_MARKED, studentToUnmark.getName()));
        } else {
            studentToUnmark.getMasteryCheck().unmark();
        }

        return new CommandResult(generateSuccessMessage(studentToUnmark));
    }

    private String generateSuccessMessage(Student studentToMark) {
        return String.format(MESSAGE_SUCCESS, studentToMark.getName());
    }
}
