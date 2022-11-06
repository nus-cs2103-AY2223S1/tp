package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;

/**
 * Deletes a student identified using its displayed index from the student map.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SINGLE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    public static final String MESSAGE_DELETE_MULTI_STUDENT_SUCCESS = "Deleted %1$s students";

    private final IndexListGenerator indicesToEdit;

    public DeleteCommand(IndexListGenerator indicesToEdit) {
        this.indicesToEdit = indicesToEdit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToDelete = new ArrayList<>();

        for (Index index : indicesToEdit.apply(model)) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            studentsToDelete.add(lastShownList.get(index.getZeroBased()));
        }

        studentsToDelete.forEach(model::deleteStudent);

        if (studentsToDelete.size() == 1) {
            return new CommandResult(String.format(MESSAGE_DELETE_SINGLE_STUDENT_SUCCESS, studentsToDelete.get(0)));
        } else {
            return new CommandResult(String.format(MESSAGE_DELETE_MULTI_STUDENT_SUCCESS, studentsToDelete.size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && indicesToEdit.equals(((DeleteCommand) other).indicesToEdit)); // indexListGenerator check
    }
}
