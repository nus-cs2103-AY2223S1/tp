package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid name
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany("^")); // only non-alphanumeric characters
        assertFalse(Company.isValidCompany("meta*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Company.isValidCompany("Meta")); // alphabets only
        assertTrue(Company.isValidCompany("12345")); // numbers only
        assertTrue(Company.isValidCompany("2Sigma")); // alphanumeric characters
        assertTrue(Company.isValidCompany("Capital Tan")); // with capital letters
        assertTrue(Company.isValidCompany("David Roger Jackson Ray Jr 2nd Nice Company Name")); // long names
    }
}
