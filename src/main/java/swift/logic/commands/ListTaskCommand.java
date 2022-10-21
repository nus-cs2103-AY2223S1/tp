package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.model.Model.PREDICATE_SHOW_ALL_TASKS;

import swift.commons.core.Messages;
import swift.model.Model;

/**
 * Finds and lists all tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all tasks as a list with\n"
            + "index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTaskCommand); // instanceof handles nulls
    }
}
