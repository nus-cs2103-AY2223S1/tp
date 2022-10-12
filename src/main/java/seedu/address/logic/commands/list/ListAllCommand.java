package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * List all tasks in NotioNUS.
 */
public class ListAllCommand extends ListCommand {

    public static final String COMMAND_WORD = "a";

    public ListAllCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(ListCommand.MESSAGE_SUCCESS);
    }
}


