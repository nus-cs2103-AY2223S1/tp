package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid company
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany("^")); // only non-alphanumeric characters
        assertFalse(Company.isValidCompany("meta*")); // contains non-alphanumeric characters

        // valid company
        assertTrue(Company.isValidCompany("shopee")); // alphabets only
        assertTrue(Company.isValidCompany("12345")); // numbers only
        assertTrue(Company.isValidCompany("tik tok v2")); // alphanumeric characters
        assertTrue(Company.isValidCompany("Jane Street")); // with capital letters
        assertTrue(Company.isValidCompany("International Consolidated Airlines Group SA")); // long company name
    }

    @Test
    public void compareTo() {
        Company alphabetCompany = new Company("Alphabet Inc");
        Company foodpandaCompany = new Company("foodpanda");
        Company foodDeliveryCompany = new Company("food delivery");
        Company ninetyNineCoCompany = new Company("99co");
        Company uppercaseACompany = new Company("A");
        Company lowercaseACompany = new Company("a");

        assertEquals(0, alphabetCompany.compareTo(new Company("Alphabet Inc"))); // Same company name
        assertTrue(alphabetCompany.compareTo(foodpandaCompany) < 0); // Different company names
        assertTrue(ninetyNineCoCompany.compareTo(alphabetCompany) < 0); // Numbers compare less than alphabets
        assertTrue(foodpandaCompany.compareTo(foodDeliveryCompany) > 0); // Spaces compare less than other characters
        assertEquals(0, uppercaseACompany.compareTo(lowercaseACompany)); // Comparison is case-insensitive
        assertTrue(lowercaseACompany.compareTo(alphabetCompany) < 0); // One name is prefix of the other
    }
}
