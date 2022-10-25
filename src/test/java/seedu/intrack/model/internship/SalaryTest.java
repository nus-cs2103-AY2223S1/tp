package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salaries
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("salary")); // non-numeric
        assertFalse(Salary.isValidSalary("9011p041")); // alphabets within digits
        assertFalse(Salary.isValidSalary("9312 1534")); // spaces within digits

        // valid salaries
        assertTrue(Salary.isValidSalary("911"));
        assertTrue(Salary.isValidSalary("93121534"));
        assertTrue(Salary.isValidSalary("124293842033123"));
    }
}
