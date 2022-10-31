package friday.logic.commands;

import java.time.LocalDate;
import java.util.List;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.student.Student;

/**
 * Marks the Mastery Check of an existing student in FRIDAY as passed.
 */
public class MarkMasteryCheckCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the Mastery Check of the student identified by"
            + " the index number as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_ALREADY_MARKED = "'s Mastery Check has already been marked as passed!";
    public static final String MESSAGE_CANNOT_PASS = "'s Mastery Check cannot be marked as passed as the date(%s) has"
            + " not been reached yet. (Today's date: %s)";

    private Index index;

    public MarkMasteryCheckCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToMark = lastShownList.get(index.getZeroBased());
        if (studentToMark.getMasteryCheck().getIsPassed()) {
            throw new CommandException(studentToMark.getName() + MESSAGE_ALREADY_MARKED);
        } else if (!studentToMark.getMasteryCheck().canPass()) {
            String str = String.format(MESSAGE_CANNOT_PASS, studentToMark.getMasteryCheck().getValue(),
                    LocalDate.now());
            throw new CommandException(studentToMark.getName() + str);
        } else {
            studentToMark.getMasteryCheck().markAsPassed();
        }

        return new CommandResult(generateSuccessMessage(studentToMark));
    }

    private String generateSuccessMessage(Student studentToMark) {
        return String.format("Marked Mastery Check of Student: %s as passed.", studentToMark.getName());
    }
}
