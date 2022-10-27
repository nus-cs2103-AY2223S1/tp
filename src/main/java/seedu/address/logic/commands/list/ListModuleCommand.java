package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;

/**
 * List all tasks in module inputted by user.
 */
public class ListModuleCommand extends ListCommand {

    public static final String COMMAND_WORD = "--module";
    private final ModuleContainsKeywordsPredicate predicate;

    public ListModuleCommand(ModuleContainsKeywordsPredicate predicate) {
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
                || (other instanceof ListModuleCommand // instanceof handles nulls
                && predicate.equals(((ListModuleCommand) other).predicate)); // state check
    }

}
