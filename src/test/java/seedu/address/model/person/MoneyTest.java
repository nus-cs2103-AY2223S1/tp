package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;

public class MoneyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Money((null)));
    }

    @Test
    public void constructor_invalidMoney_throwsIllegalArgumentException() {
        Integer invalidMoney = -1;
        assertThrows(IllegalArgumentException.class, () -> new Money(invalidMoney));
    }

    @Test
    public void isValidMoneyTest() {
        // null amount of money
        assertThrows(NullPointerException.class, () -> Money.isValidMoney(null));

        // invalid amount of money
        assertFalse(Money.isValidMoney(-1));

        // valid amount of money
        assertTrue(Money.isValidMoney(1)); // single digit positive number
        assertTrue(Money.isValidMoney(1234)); // multiple digits positive number
        assertTrue(Money.isValidMoney(0)); // zero
    }

    @Test
    public void isGreaterThanZeroTest() {
        Money moneyWithZeroAmount = new Money(0);
        assertFalse(moneyWithZeroAmount.isGreaterThanZero());

        Money moneyWithPositiveAmount = new Money(50);
        assertTrue(moneyWithPositiveAmount.isGreaterThanZero());
    }

    @Test
    public void moneySummationTest() {
        Money moneyWithZeroAmount = new Money(0);
        Money moneyWithFiveAmount = new Money(5);
        Money moneyWithTenAmount = new Money(10);
        Money moneyWithMaximumValue = new Money(Integer.MAX_VALUE);

        try {
            Money zeroPlusFive = moneyWithZeroAmount.addTo(moneyWithFiveAmount);
            Money tenPlusFive = moneyWithTenAmount.addTo(moneyWithFiveAmount);
            assertEquals(new Money(5), zeroPlusFive);
            assertEquals(new Money(15), tenPlusFive);
        } catch (CommandException e) {
            // Should not throw an error because 5 and 10 is still smaller than the maximum int value
            assert false;
        }

        assertThrows(CommandException.class, () -> moneyWithMaximumValue.addTo(moneyWithFiveAmount));
    }
}
