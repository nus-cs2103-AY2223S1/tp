package friday.logic.commands;

import static friday.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.student.Student;

/**
 * class to handle mark mastery check
 */
public class MarkMasteryCheckCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the Mastery Check of the person identified by"
            + " the index number as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_ALREADY_MARKED = "'s Mastery Check has already been marked as passed!";

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
        } else {
            studentToMark.getMasteryCheck().markAsPassed();
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(generateSuccessMessage(studentToMark));
    }

    private String generateSuccessMessage(Student studentToMark) {
        return String.format("Marked Mastery Check of Student: %s as passed.", studentToMark.getName());
    }
}
