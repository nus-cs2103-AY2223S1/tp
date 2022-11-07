package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all items in Salesy.
 */
public class ListAllCommand extends Command {
    public static final String COMMAND_WORD = "listAll";

    public static final String MESSAGE_SUCCESS = "Listed all entities in Salesy!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        model.updateFilteredSupplyItemList(Model.PREDICATE_SHOW_ALL_SUPPLY_ITEMS);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
