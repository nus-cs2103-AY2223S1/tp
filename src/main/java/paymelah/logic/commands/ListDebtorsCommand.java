package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import paymelah.model.Model;
import paymelah.model.person.Person;

/**
 * Lists all persons with debts in the address book to the user.
 */
public class ListDebtorsCommand extends Command {

    public static final String COMMAND_WORD = "listdebtors";

    public static final String MESSAGE_SUCCESS = "Listed all persons with debts";
    public static final String MESSAGE_NO_DEBTORS = "There are no persons with debts";

    public static final Predicate<Person> PREDICATE_SHOW_DEBTORS = p -> !p.getDebts().isEmpty();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_DEBTORS);
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_DEBTORS);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
