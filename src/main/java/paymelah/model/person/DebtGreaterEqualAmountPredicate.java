package paymelah.model.person;

import java.util.function.Predicate;

import paymelah.model.debt.Money;

/**
 * Tests that a {@code Person}'s {@code DebtList} has a sum greater or equal to the amount given.
 */
public class DebtGreaterEqualAmountPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS =
            "Money amounts should use numbers to represent the amount in dollars,"
                    + " separating dollar and cent values with a .";

    private final Money amount;

    public DebtGreaterEqualAmountPredicate(Money money) {
        amount = money;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public boolean test(Person person) {
        return person.getDebtsAmountAsMoney().compareTo(amount) >= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DebtGreaterEqualAmountPredicate // instanceof handles nulls
                && amount.equals(((DebtGreaterEqualAmountPredicate) other).amount)); // state check
    }
}
