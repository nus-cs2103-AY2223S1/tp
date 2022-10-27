package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * List all archived tasks.
 */
public class ShowArchiveCommand extends Command {

    public static final String COMMAND_WORD = "showArchive";
    public static final String MESSAGE_SUCCESS = "Listed all archived tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredArchivedTaskList(model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

}
