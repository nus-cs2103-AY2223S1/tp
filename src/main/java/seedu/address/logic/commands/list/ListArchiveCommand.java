package seedu.address.logic.commands.list;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * List all archived tasks.
 */
public class ListArchiveCommand extends ListCommand {

    public static final String COMMAND_WORD = "-archive";
    public static final String MESSAGE_SUCCESS = "Listed all archived tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }

}
