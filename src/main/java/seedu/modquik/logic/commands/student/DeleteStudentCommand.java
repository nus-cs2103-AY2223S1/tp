package seedu.modquik.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.student.Student;

/**
 * Deletes a person identified using it's displayed index from ModQuik.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "delete student";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified student from the list of students.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final Index targetIndex;

    public DeleteStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete), ModelType.STUDENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStudentCommand) other).targetIndex)); // state check
    }
}
