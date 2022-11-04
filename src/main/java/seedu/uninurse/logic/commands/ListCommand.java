package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.uninurse.model.Model;

/**
 * Lists all persons in the uninurse book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all persons";
    public static final CommandType LIST_COMMAND_TYPE = CommandType.LIST;

    @Override
    public CommandResult execute(Model model) {
        requireAllNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.LIST);
    }
}
