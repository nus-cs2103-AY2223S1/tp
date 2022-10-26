package seedu.address.logic.commands.list;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

public class ListArchivedModuleCommand extends ListCommand {

    public static final String COMMAND_WORD = "--module";
    private final ModuleContainsKeywordsPredicate predicate;

    public ListArchivedModuleCommand(ModuleContainsKeywordsPredicate predicate) {
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
