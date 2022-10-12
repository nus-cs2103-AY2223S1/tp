package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.FilterPredicate;
import seedu.address.model.task.TaskBelongsToModulePredicate;

/**
 * Filters the task list by module.
 */
public class FilterTasksCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the task list by module. "
            + "Parameters: "
            + PREFIX_MODULE + "MODULE";

    public static final String MESSAGE_SUCCESS = "Listed all tasks with following constraints:%1$s";

    public static final String MESSAGE_NO_RESULTS = "No tasks found with following constraints:%1$s";

    public static final String MODULE_NOT_FOUND = "This module does not exist";

//    private final TaskBelongsToModulePredicate predicate;
    private final FilterPredicate predicate;

    /**
     * Constructor of the FilterTaskCommand class which filters the task list by module.
     *
     * @param predicate
     */
//    public FilterTasksCommand(TaskBelongsToModulePredicate predicate) {
//        this.predicate = predicate;
//    }

    public FilterTasksCommand(FilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);

        if (predicate.hasModuleToCheck() && !model.hasModule(predicate.getModuleToCheck())) {
            throw new CommandException(MODULE_NOT_FOUND);
        }

        if (model.getFilteredTaskList().isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_RESULTS, predicate));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, predicate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTasksCommand // instanceof handles nulls
                && predicate.equals(((FilterTasksCommand) other).predicate)); // state check
    }

}
