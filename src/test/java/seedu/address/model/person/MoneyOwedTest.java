package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class MoneyOwedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MoneyOwed((null)));
    }

    @Test
    public void constructor_invalidMoneyOwed_throwsIllegalArgumentException() {
        Integer invalidMoneyOwed = -1;
        assertThrows(IllegalArgumentException.class, () -> new MoneyOwed(invalidMoneyOwed));
    }

    @Test
    public void isValidAddress() {
        // null amount of money owed
        assertThrows(NullPointerException.class, () -> MoneyOwed.isValidMoneyOwed(null));

        // invalid amount of money owed
        assertFalse(MoneyOwed.isValidMoneyOwed(-1));

        // valid amount of money owed
        assertTrue(MoneyOwed.isValidMoneyOwed(1)); // single digit positive number
        assertTrue(MoneyOwed.isValidMoneyOwed(1234)); // multiple digits positive number
        assertTrue(MoneyOwed.isValidMoneyOwed(0)); // zero
    }
}
