package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


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
}
