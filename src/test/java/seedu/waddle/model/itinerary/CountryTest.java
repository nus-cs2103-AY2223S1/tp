package seedu.waddle.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Country(null));
    }

    @Test
    public void constructor_invalidCountry_throwsIllegalArgumentException() {
        String invalidCountry = "";
        assertThrows(IllegalArgumentException.class, () -> new Country(invalidCountry));
    }

    @Test
    public void isValidCountry() {
        // null country
        assertThrows(NullPointerException.class, () -> Country.isValidCountry(null));

        // invalid country
        assertFalse(Country.isValidCountry("")); // empty string
        assertFalse(Country.isValidCountry(" ")); // spaces only
        assertFalse(Country.isValidCountry("Singa*pore"));

        // valid country
        assertTrue(Country.isValidCountry("Singapore"));
        assertTrue(Country.isValidCountry("Peru"));
        assertTrue(Country.isValidCountry("United States"));
    }
}
