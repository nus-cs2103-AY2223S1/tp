package seedu.address.logic.commands.task;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

public class ReminderCommand extends Command {

    public static final String COMMAND_WORD = "remindT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows tasks with deadlines up to and including the "
            + "date provided. "
            + "Parameters: DEADLINE (must be in dd-mm-yyyy format)\n"
            + "Example: " + COMMAND_WORD + " "
            + "12-09-2022";

    private final TaskContainsKeywordsPredicate predicate;

    public static final String REMINDER_SUCCESS = "Here are your upcoming deadlines.";

    public ReminderCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(REMINDER_SUCCESS);
    }


}
