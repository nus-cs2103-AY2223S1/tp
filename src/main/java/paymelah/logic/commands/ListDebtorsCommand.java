package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.model.debt.Money.ZERO_MONEY;

import java.util.function.Predicate;

import paymelah.model.Model;
import paymelah.model.person.DebtGreaterEqualAmountPredicate;
import paymelah.model.person.Person;

/**
 * Lists all persons with debts in the address book to the user.
 */
public class ListDebtorsCommand extends Command {

    public static final String COMMAND_WORD = "listdebtors";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all persons who owe more than a certain amount. "
            + "If no amount is provided, lists all persons who owe any debt. "
            + "Parameters: "
            + "[" + PREFIX_MONEY + "<money>]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MONEY + "20.00";

    public static final String MESSAGE_SUCCESS_SPECIFIC = "Listed all persons with debts amounting to more than "
                                                            + "or equal to $%1$s.";
    public static final String MESSAGE_SUCCESS_GENERIC = "Listed all persons with debts.";
    public static final String MESSAGE_NO_DEBTORS = "There are no persons with debts";

    private final Predicate<Person> predicate;

    public ListDebtorsCommand(DebtGreaterEqualAmountPredicate predicate) {
        this.predicate = predicate;
    }

    public ListDebtorsCommand() {
        this.predicate = p -> !p.getDebts().isEmpty() && p.getDebtsAmountAsMoney().compareTo(ZERO_MONEY) > 0;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_DEBTORS);
        } else if (predicate instanceof DebtGreaterEqualAmountPredicate) {
            DebtGreaterEqualAmountPredicate p = (DebtGreaterEqualAmountPredicate) predicate;
            return new CommandResult(String.format(MESSAGE_SUCCESS_SPECIFIC, p.getAmount()));
        } else {
            return new CommandResult(MESSAGE_SUCCESS_GENERIC);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListDebtorsCommand // instanceof handles nulls
                && predicate.equals(((ListDebtorsCommand) other).predicate)); // state check
    }
}
