package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.Assert;

public class CuisineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Cuisine(null));
    }

    @Test
    public void constructor_invalidCuisine_throwsIllegalArgumentException() {
        String invalidEmail = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Cuisine(invalidEmail));
    }

    @Test
    public void isValidCuisine() {
        // null cuisine
        Assert.assertThrows(NullPointerException.class, () -> Cuisine.isValidCuisine(null));

        // invalid cuisine
        assertFalse(Cuisine.isValidCuisine("")); // empty string
        assertFalse(Cuisine.isValidCuisine(" ")); // spaces only
        assertFalse(Cuisine.isValidCuisine("^")); // only non-alphanumeric characters
        assertFalse(Cuisine.isValidCuisine("peter*")); // contains non-alphanumeric characters

        // valid cuisine
        assertTrue(Cuisine.isValidCuisine("peterjack")); // alphabets only
        assertTrue(Cuisine.isValidCuisine("CapitalTan")); // with capital letters
        assertTrue(Cuisine.isValidCuisine("abc def")); // whitespace
        assertTrue(Cuisine.isValidCuisine("DavidRogerJacksonRayJrnd")); // long cuisine
    }
}
