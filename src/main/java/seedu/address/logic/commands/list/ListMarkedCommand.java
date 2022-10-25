package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.TaskIsDonePredicate;

/**
 * List all unmarked tasks in NotionUS.
 */
public class ListMarkedCommand extends ListCommand {

    public static final String COMMAND_WORD = "-m";
    public final TaskIsDonePredicate predicate;

    public ListMarkedCommand(TaskIsDonePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.updateFilterStatus(predicate.toString(), true);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListMarkedCommand // instanceof handles nulls
                && predicate.equals(((ListMarkedCommand) other).predicate)); // state check
    }
}
