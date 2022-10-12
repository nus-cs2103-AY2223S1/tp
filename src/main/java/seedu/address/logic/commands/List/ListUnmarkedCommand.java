package seedu.address.logic.commands.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.ModuleIsDonePredicate;

import static java.util.Objects.requireNonNull;

public class ListUnmarkedCommand extends ListCommand{

    public static final String COMMAND_WORD = "-u";
    public final ModuleIsDonePredicate predicate;

    public ListUnmarkedCommand(ModuleIsDonePredicate predicate) {
        super(MESSAGE_SUCCESS);
        this.predicate = predicate;

    }

    public void newMessageSuccess() {
        MESSAGE_SUCCESS += "that has not been marked";
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
                && (predicate.equals(((ListUnmarkedCommand) other).predicate))); // state check
    }
}
