package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.task.TaskLinkedToExamPredicate;

/**
 * Shoes all tasks linked to a specific exam.
 */
public class ListExamTasksCommand extends Command {

    public static final String COMMAND_WORD = "showt";

    public static final String MESSAGE_USAGE = "e " + COMMAND_WORD
            + ": Show all tasks linked to a specific exam identified by the index number used in the exam list.\n"
            + "Parameter: INDEX\n"
            + "Example: e " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed all tasks from exam: %1$s";

    public static final String MESSAGE_NO_RESULTS = "No tasks found from exam: %1$s";

    private final Index examIndex;

    /**
     * Constructor of the ListExamTasksCommand class which shows all tasks linked to a specific exam.
     *
     * @param examIndex Index of exam to be checked if tasks are linked with.
     */
    public ListExamTasksCommand(Index examIndex) {
        this.examIndex = examIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exam> lastShownExamList = model.getFilteredExamList();

        if (examIndex.getZeroBased() >= lastShownExamList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        Exam examToCheck = lastShownExamList.get(examIndex.getZeroBased());

        TaskLinkedToExamPredicate predicate = new TaskLinkedToExamPredicate(examToCheck);

        model.updateFilteredTaskList(predicate);

        if (model.getFilteredTaskList().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_RESULTS, examToCheck));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, examToCheck));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListExamTasksCommand // instanceof handles nulls
                && examIndex.equals(((ListExamTasksCommand) other).examIndex)); // state check
    }
}
