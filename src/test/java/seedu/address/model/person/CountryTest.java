package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Country(null));
    }


    @Test
    public void isValidCountry() {
        // null country
        assertThrows(NullPointerException.class, () -> Country.isValidCountry(null));

        // valid country
        assertTrue(Country.isValidCountry("singapore")); // alphabets only
        assertTrue(Country.isValidCountry("United States of America")); // alphabets with capital letters only
        assertTrue(Country.isValidCountry(
                "Al Jumahiriyah al Arabiyah al Libiyah ash Shabiyah al Ishtirakiyah al Uzma")); // long country

        // invalid country
        assertFalse(Country.isValidCountry("54th street in London")); // alphanumeric letters;
        assertFalse(Country.isValidCountry("best.country.ever")); // with symbols
    }
}


