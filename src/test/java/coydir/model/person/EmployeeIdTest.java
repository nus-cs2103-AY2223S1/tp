package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import coydir.testutil.TestUtil;

public class EmployeeIdTest {

    @Test
    public void constructor_noArgument_success() {
        TestUtil.restartEmployeeId(1);
        // create five employee ids
        EmployeeId one = new EmployeeId();
        EmployeeId two = new EmployeeId();
        EmployeeId three = new EmployeeId();
        EmployeeId four = new EmployeeId();
        EmployeeId five = new EmployeeId();

        // check all ids
        assertTrue(one.value.equals("1"));
        assertTrue(two.value.equals("2"));
        assertTrue(three.value.equals("3"));
        assertTrue(four.value.equals("4"));
        assertTrue(five.value.equals("5"));

        // check count
        int count = EmployeeId.getCount();
        assertTrue(count == 1 + 5);

        // check id tracking
        int size = EmployeeId.getCurrentIdsCount();
        assertTrue(size == 5);
    }

    @Test
    public void restart_validCount_success() {
        EmployeeId.restart(1);
        assertTrue(EmployeeId.getCount() == 1); // basic check, should always pass
        for (int i = 0; i < 100; i++) {
            new EmployeeId();
        }
        EmployeeId.restart(10);
        assertTrue(EmployeeId.getCount() == 10); // count reset to 10
        assertTrue(EmployeeId.getCurrentIdsCount() == 0); // no ids recorded
        EmployeeId next = new EmployeeId(); // check new id still works
        assertTrue(next.value.equals("10")
                && EmployeeId.getCount() == 11
                && EmployeeId.getCurrentIdsCount() == 1);
    }

    @Test
    public void addEmployeeId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> EmployeeId.addEmployeeId(null));
    }

    @Test
    public void addEmployeeId_invalidArgument_throwsIllegalArgumentException() {
        String invalidNumber = "abc";
        String invalidEmployeeId = "0";
        assertThrows(IllegalArgumentException.class, () -> EmployeeId.addEmployeeId(invalidNumber));
        assertThrows(IllegalArgumentException.class, () -> EmployeeId.addEmployeeId(invalidEmployeeId));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmployeeId(null));
    }

    @Test
    public void constructor_invalidArgument_throwsIllegalArgumentException() {
        String invalidEmployeeId = "abc";
        assertThrows(IllegalArgumentException.class, () -> new EmployeeId(invalidEmployeeId));
    }

    @Test
    public void isValidNumber() {
        // null
        assertThrows(NullPointerException.class, () -> EmployeeId.isValidNumber(null));

        // invalid number
        assertFalse(EmployeeId.isValidNumber("")); // empty string
        assertFalse(EmployeeId.isValidNumber(" ")); // spaces only
        assertFalse(EmployeeId.isValidNumber("one")); // non-numeric
        assertFalse(EmployeeId.isValidNumber("12four")); // alphabets within digits
        assertFalse(EmployeeId.isValidNumber("9 4")); // spaces within digits
        assertFalse(EmployeeId.isValidNumber("-1")); // negative number

        // valid numbers
        assertTrue(EmployeeId.isValidNumber("0")); // smallest possible
        assertTrue(EmployeeId.isValidNumber("1")); // smallest possible id
        assertTrue(EmployeeId.isValidNumber("13")); // normal
        assertTrue(EmployeeId.isValidNumber("2147483647")); // max int
        assertTrue(EmployeeId.isValidNumber("21474836470")); // more than max int
    }

    @Test
    public void isValidEmployeeId() {
        // null id
        assertThrows(NullPointerException.class, () -> EmployeeId.isValidEmployeeId(null));

        // invalid ids (always)
        assertFalse(EmployeeId.isValidEmployeeId("")); // empty string
        assertFalse(EmployeeId.isValidEmployeeId(" ")); // spaces only
        assertFalse(EmployeeId.isValidEmployeeId("one")); // non-numeric
        assertFalse(EmployeeId.isValidEmployeeId("12four")); // alphabets within digits
        assertFalse(EmployeeId.isValidEmployeeId("9 4")); // spaces within digits
        assertFalse(EmployeeId.isValidEmployeeId("-1")); // negative value

        // valid ids
        TestUtil.restartEmployeeId(1000000); // any number less than 1000000 and more than 0 is valid
        assertTrue(EmployeeId.isValidEmployeeId("1"));
        assertTrue(EmployeeId.isValidEmployeeId("100"));
        assertTrue(EmployeeId.isValidEmployeeId("999999"));

        // invalid ids
        assertFalse(EmployeeId.isValidEmployeeId("0")); // zero is invalid
        assertFalse(EmployeeId.isValidEmployeeId("1000000")); // next employee id
        assertFalse(EmployeeId.isValidEmployeeId("9999999")); // out of range
        assertFalse(EmployeeId.isValidEmployeeId("2147483647")); // max int
        // note: not testing for more than max int (not in scope)

        // testing employee id boundary
        TestUtil.restartEmployeeId(10);
        assertTrue(EmployeeId.isValidEmployeeId("5")); // normal
        assertTrue(EmployeeId.isValidEmployeeId("9")); // max possible
        assertFalse(EmployeeId.isValidEmployeeId("10")); // next id
        assertFalse(EmployeeId.isValidEmployeeId("13")); // out of max range
    }

    @Test
    public void equals() {
        // create a base for comparison
        TestUtil.restartEmployeeId(2);
        EmployeeId base = new EmployeeId("1");

        assertTrue(base.equals(base)); // equal to itself
        
        EmployeeId other = new EmployeeId(base.value);
        assertTrue(base.equals(other)); // equal to another instance
        
        TestUtil.restartEmployeeId(1);
        other = new EmployeeId();
        assertTrue(base.equals(other)); // equal to new one created by default constructor

        assertFalse(base.equals(new EmployeeId())); // different id value
    }

}
