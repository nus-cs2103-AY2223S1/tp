package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from TA-Assist.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "> Deletes the student identified by the index number used in the "
            + "displayed student list.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Student: [ %1$s ]";

    private final Index index;

    /**
     * Creates a DeleteCommand to delete the student at the given index.
     */
    public DeleteCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(index.getZeroBased());
        model.removeStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToDelete.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && index.equals(((DeleteCommand) other).index)); // state check
    }
}
