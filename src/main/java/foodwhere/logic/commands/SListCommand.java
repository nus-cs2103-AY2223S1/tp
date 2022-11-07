package foodwhere.logic.commands;

import static foodwhere.model.Model.PREDICATE_SHOW_ALL_STALLS;
import static java.util.Objects.requireNonNull;

import foodwhere.model.Model;

/**
 * Lists all stalls in the address book in FoodWhere for the user.
 */
public class SListCommand extends Command {

    public static final String COMMAND_WORD = "slist";

    public static final String MESSAGE_SUCCESS = "Listed all stalls";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
