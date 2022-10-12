package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.ModuleIsDonePredicate;

/**
 * List all unmarked tasks in NotioNUS.
 */
public class ListUnmarkedCommand extends ListCommand {

    public static final String COMMAND_WORD = "-u";
    public final ModuleIsDonePredicate predicate;

    public ListUnmarkedCommand(ModuleIsDonePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListUnmarkedCommand // instanceof handles nulls
                && predicate.equals(((ListUnmarkedCommand) other).predicate)); // state check
    }
}
