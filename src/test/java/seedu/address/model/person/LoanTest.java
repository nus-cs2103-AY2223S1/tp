package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LoanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Loan(null));
    }

    @Test
    public void constructor_invalidLoan_throwsIllegalArgumentException() {
        String invalidLoan = "";
        assertThrows(IllegalArgumentException.class, () -> new Loan(invalidLoan));
    }

    @Test
    public void isValidLoan() {
        // null loan
        assertThrows(NullPointerException.class, () -> Loan.isValidLoan(null));

        // invalid loan strings
        assertFalse(Loan.isValidLoan("")); // empty string
        assertFalse(Loan.isValidLoan(" ")); // spaces only
        assertFalse(Loan.isValidLoan("^&92")); // unacceptable symbols
        assertFalse(Loan.isValidLoan("loan")); // non-numeric
        assertFalse(Loan.isValidLoan("9p0")); // alphabets within digits
        assertFalse(Loan.isValidLoan("2 4")); // spaces within digits
        assertFalse(Loan.isValidLoan("2 4")); // spaces within digits
        assertFalse(Loan.isValidLoan("2$4")); // dollar sign in the middle

        // valid phone numbers
        assertTrue(Loan.isValidLoan("-12")); // negative numbers
        assertTrue(Loan.isValidLoan("-10.57")); // negative numbers with decimal point
        assertTrue(Loan.isValidLoan("12")); // positive numbers
        assertTrue(Loan.isValidLoan("11.88")); // positive numbers with decimal point
        assertTrue(Loan.isValidLoan("11.882399123")); // very precise decimal point
        assertTrue(Loan.isValidLoan("19946024893")); // large positive number
        assertTrue(Loan.isValidLoan("-19946024893")); // large negative number
        assertTrue(Loan.isValidLoan("-$190")); // dollar sign gets parsed out
        assertTrue(Loan.isValidLoan("$53")); // dollar sign gets parsed out
        assertTrue(Loan.isValidLoan("53$")); // dollar sign gets parsed out
    }

    @Test
    public void deepCopy_copyNotSameButEqual() {
        Loan loan = new Loan("309");
        Loan deepCopy = loan.deepCopy();
        assertNotSame(loan, deepCopy);
        assertEquals(loan, deepCopy);
    }
}
