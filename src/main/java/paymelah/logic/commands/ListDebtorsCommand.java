package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.model.Model.PREDICATE_SHOW_FRIENDS; //placeholder for predicate that shows debtors

import paymelah.model.Model;

/**
 * Lists all persons with debts in the address book to the user.
 */
public class ListDebtorsCommand extends Command {

    public static final String COMMAND_WORD = "listdebtors";

    public static final String MESSAGE_SUCCESS = "Listed all persons with debts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_FRIENDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
