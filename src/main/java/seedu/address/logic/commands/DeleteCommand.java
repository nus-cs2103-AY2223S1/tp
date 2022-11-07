package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.storage.ClassStorage;

/**
 * Deletes a student identified using its displayed index from the student list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX [OPTIONAL INDEXES] (must be positive integer(s))\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deletion successful!";
    public static final String STUDENT_LIST_EMPTY_ERROR = "There is no student to delete.";

    private final List<Index> targetIndexes;

    /**
     * Creates a DeleteCommand to delete the specified {@code Student}(s).
     *
     * @param targetIndexes of the student(s) to delete.
     */
    public DeleteCommand(List<Index> targetIndexes) {
        Collections.sort(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (model.getFilteredStudentList().size() == 0) {
            throw new CommandException(STUDENT_LIST_EMPTY_ERROR);
        }

        for (int i = targetIndexes.size() - 1; i >= 0; i--) {
            Index targetIndex = targetIndexes.get(i);
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_INDEXES);
            }
            Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteStudent(studentToDelete);
            ClassStorage.removeExistingClass(studentToDelete);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }
}
