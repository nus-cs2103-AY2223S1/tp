package paymelah.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import paymelah.model.debt.Money;
import paymelah.testutil.DebtBuilder;
import paymelah.testutil.PersonBuilder;

public class DebtGreaterEqualAmountPredicateTest {

    @Test
    void equals() {
        Money firstAmount = new Money("5.00");
        Money secondAmount = new Money("10.5");

        DebtGreaterEqualAmountPredicate firstPredicate = new DebtGreaterEqualAmountPredicate(firstAmount);
        DebtGreaterEqualAmountPredicate secondPredicate = new DebtGreaterEqualAmountPredicate(secondAmount);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DebtGreaterEqualAmountPredicate firstPredicateCopy = new DebtGreaterEqualAmountPredicate(firstAmount);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void moneyGreaterEqualAmount_returnsTrue() {
        Money amount = new Money("5.00");
        DebtGreaterEqualAmountPredicate predicate = new DebtGreaterEqualAmountPredicate(amount);

        // Single debt greater than amount
        Person dude = new PersonBuilder().withDebts(new DebtBuilder().withMoney("10.55").build()).build();
        assertTrue(predicate.test(dude));

        // Multiple debts whose sum greater than amount
        dude = new PersonBuilder().withDebts(
                                    new DebtBuilder().withMoney("$4").build(), new DebtBuilder().withMoney("3").build())
                                .build();
        assertTrue(predicate.test(dude));

        // Single debt exactly equal to amount
        dude = new PersonBuilder().withDebts(new DebtBuilder().withMoney("$5.0").build()).build();
        assertTrue(predicate.test(dude));

        // Multiple debts sum exactly equal to amount
        dude = new PersonBuilder().withDebts(
                                    new DebtBuilder().withMoney("$4").build(), new DebtBuilder().withMoney("1").build())
                                .build();
        assertTrue(predicate.test(dude));
    }

    @Test
    public void moneyGreaterEqualAmount_returnsFalse() {
        Money amount = new Money("5.00");
        DebtGreaterEqualAmountPredicate predicate = new DebtGreaterEqualAmountPredicate(amount);

        // Single debt less than amount
        Person dude = new PersonBuilder().withDebts(new DebtBuilder().withMoney("$4.55").build()).build();
        assertFalse(predicate.test(dude));

        // Multiple debts whose sum less than amount
        dude = new PersonBuilder().withDebts(
                                    new DebtBuilder().withMoney("$2").build(), new DebtBuilder().withMoney("1").build())
                                .build();
        assertFalse(predicate.test(dude));
    }
}
