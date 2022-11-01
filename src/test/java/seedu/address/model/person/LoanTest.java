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
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("")); // nothing given
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("99.999")); // 3dp
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("9999999999999")); // too large
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("+$1000000000000.01")); // too large
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("-1000000000000.01")); // too small
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("$-99.8")); // dollar sign before minus
        assertThrows(IllegalArgumentException.class, () ->
                new Loan("-+99.8")); // both signs at the same time
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
        assertFalse(Loan.isValidLoan("11.88234")); // more than 2 decimal places
        assertFalse(Loan.isValidLoan("2 4")); // spaces within digits
        assertFalse(Loan.isValidLoan("2$4")); // dollar sign in the middle
        assertFalse(Loan.isValidLoan("1000000000000.01")); // one cent more than max
        assertFalse(Loan.isValidLoan("-1000000000000.01")); // one cent less than min
        assertFalse(Loan.isValidLoan("-1111111111111")); // number too small
        assertFalse(Loan.isValidLoan("1732744702493240347")); // number too large
        assertFalse(Loan.isValidLoan("-.3")); // missing ones place

        // valid phone numbers
        assertTrue(Loan.isValidLoan("-12")); // negative numbers
        assertTrue(Loan.isValidLoan("-10.57")); // negative numbers with decimal point
        assertTrue(Loan.isValidLoan("-10.3")); // negative numbers with one decimal place
        assertTrue(Loan.isValidLoan("12")); // positive numbers
        assertTrue(Loan.isValidLoan("11.88")); // positive numbers with decimal point
        assertTrue(Loan.isValidLoan("19946024893")); // large positive number
        assertTrue(Loan.isValidLoan("-19946024893")); // large negative number
        assertTrue(Loan.isValidLoan("-$190")); // dollar sign gets parsed out
        assertTrue(Loan.isValidLoan("$53")); // dollar sign gets parsed out
        assertTrue(Loan.isValidLoan("+$53")); // dollar sign with plus sign parsed out
        assertTrue(Loan.isValidLoan("+53")); // plus sign parsed out
        assertTrue(Loan.isValidLoan("1000000000000")); // maximum value
        assertTrue(Loan.isValidLoan("1000000000000")); // minimum value
        assertTrue(Loan.isValidLoan(1_000_000_000_000.00)); // max value
        assertTrue(Loan.isValidLoan(-1_000_000_000_000.00)); // max value
        assertTrue(Loan.isValidLoan(999_999_999_999.0)); // one dollar below max value
        assertTrue(Loan.isValidLoan(999_999_999_999.99)); // one cent below max value
        assertTrue(Loan.isValidLoan(-999_999_999_999.0)); // one dollar above min value
        assertTrue(Loan.isValidLoan(-999_999_999_999.99)); // one cent above min value
        assertTrue(Loan.isValidLoan("-$999999999999.99")); // one cent above min value
        assertTrue(Loan.isValidLoan("$999999999999.99")); // one cent above min value
    }

    @Test
    public void loanAmountsAreTheSame() {
        assertEquals(new Loan("33.55").getAmount(), 33.55);
        assertEquals(new Loan("1000000000000").getAmount(), 1_000_000_000_000.0);
        assertEquals(new Loan("999999999999").getAmount(), 999_999_999_999.0);
        assertEquals(new Loan("999999999999.9").getAmount(), 999_999_999_999.9);
        assertEquals(new Loan("999999999999.99").getAmount(), 999_999_999_999.99);
        assertEquals(new Loan("0.55").getAmount(), 0.55);
        assertEquals(new Loan("-0.55").getAmount(), -0.55);
        assertEquals(new Loan("-999999999999.99").getAmount(), -999_999_999_999.99);
    }

    @Test
    public void deepCopy_copyNotSameButEqual() {
        Loan loan = new Loan("309");
        Loan deepCopy = loan.deepCopy();
        assertNotSame(loan, deepCopy);
        assertEquals(loan, deepCopy);
    }
}
