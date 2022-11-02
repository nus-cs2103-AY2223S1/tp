package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;

/**
 * Deletes an exam identified using it's displayed index from the address book. All tasks currently linked to that exam
 * will become unlinked.
 */
public class DeleteExamCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = "e " + COMMAND_WORD
            + ": Deletes the exam identified by the index number used in the displayed exam list.\n"
            + "Parameters: INDEX\n"
            + "Example: e " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EXAM_SUCCESS = "Deleted Exam: %1$s";

    private final Index targetIndex;

    public DeleteExamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exam> lastShownList = model.getFilteredExamList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        Exam examToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.unlinkTasksFromExam(examToDelete);
        model.deleteExam(examToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_EXAM_SUCCESS, examToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteExamCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteExamCommand) other).targetIndex)); // state check
    }
}
