package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.commons.FilterInfo;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsCategoryPredicate;

/**
 * Filters and lists all tasks in address book with category that matches the argument category.
 */
public class FilterTaskCategoryCommand extends Command {

    public static final String COMMAND_WORD = "filterByCategory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all tasks with TaskCategory that matches the "
            + "input task category and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_CATEGORY + "CATEGORY (database/frontend/backend/uiux/presentation/others)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CATEGORY + "backend";

    private final TaskContainsCategoryPredicate predicate;
    private final FilterInfo filterInfo;

    /**
     * @param predicate  to filter with
     * @param filterInfo to show in the TaskListInfo
     */
    public FilterTaskCategoryCommand(TaskContainsCategoryPredicate predicate, FilterInfo filterInfo) {
        this.predicate = predicate;
        this.filterInfo = filterInfo;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size()), filterInfo);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTaskCategoryCommand // instanceof handles nulls
                && predicate.equals(((FilterTaskCategoryCommand) other).predicate)); // state check
    }
}
