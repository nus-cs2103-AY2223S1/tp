package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.model.Model;
import seedu.application.model.ShowArchiveOnlyPredicate;

public class ListArchiveCommand extends Command {
    public static final String COMMAND_WORD = "list-archive";

    public static final String MESSAGE_SUCCESS = "Listed all archives";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ShowArchiveOnlyPredicate showArchiveOnlyPredicate =
                new ShowArchiveOnlyPredicate(model.getArchiveList());
        model.updateFilteredApplicationList(showArchiveOnlyPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
