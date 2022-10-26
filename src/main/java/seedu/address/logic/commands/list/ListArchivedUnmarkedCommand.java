package seedu.address.logic.commands.list;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.ModuleIsDonePredicate;

import static java.util.Objects.requireNonNull;

public class ListArchivedUnmarkedCommand extends ListCommand {

    public static final String COMMAND_WORD = "--module";
    private final ModuleIsDonePredicate predicate;

    public ListArchivedUnmarkedCommand(ModuleIsDonePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredArchivedTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredArchivedTaskList().size()), false, false, true
        );
    }
}
