package friday.logic.commands;

import static friday.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.student.Student;

/**
 * Class to handle unmarking mastery check
 */
public class UnmarkMasteryCheckCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the Mastery Check of the student identified by"
            + " the index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_NOT_MARKED = "Can't unmark as %s's Mastery Check has not marked as passed!";

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
        if (!studentToUnmark.getMasteryCheck().getIsPassed()) {
            throw new CommandException(String.format(MESSAGE_NOT_MARKED, studentToUnmark.getName()));
        } else {
            studentToUnmark.getMasteryCheck().unmark();
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(generateSuccessMessage(studentToUnmark));
    }

    private String generateSuccessMessage(Student studentToMark) {
        return String.format("Unmarked Mastery Check of Student: %s", studentToMark.getName());
    }
}
