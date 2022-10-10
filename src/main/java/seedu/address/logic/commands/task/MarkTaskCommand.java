package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Marks a task of a person as complete or incomplete.
 */
public class MarkTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "mark";

    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Marks a task to be complete "
            + "Parameters: "
            + "TITLE "
            + "Example: " + COMMAND_WORD + " "
            + "Mark task functionality";

    public static final String MESSAGE_ARGUMENTS = "Task Index: %1$d";

    public static final String MESSAGE_SUCCESS = "Task %1$d is marked as complete";

    private final Index task_index;
    //private final Index person_index;

    /**
     * @param task_index of the person's task to be updated
     * @param person_index of the person to be updated
     */
    public MarkTaskCommand(Index task_index, Index person_index) {
        requireAllNonNull(task_index, person_index);

        this.task_index = task_index;
        //this.person_index = person_index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        throw new CommandException(String.format(MESSAGE_SUCCESS, task_index.getOneBased(), task_index.getOneBased()));
        //return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkTaskCommand)) {
            return false;
        }

        // state check
        MarkTaskCommand e = (MarkTaskCommand) other;
        return task_index.equals(e.task_index);
    }
}
