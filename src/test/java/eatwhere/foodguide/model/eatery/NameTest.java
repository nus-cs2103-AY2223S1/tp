package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.Assert;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertTrue(Name.isValidName("^")); // only non-alphanumeric characters
        assertTrue(Name.isValidName("12345")); // only numbers
    }

    @Test
    public void isSimilarName() {
        Name baseName = new Name("Alice");

        assertTrue(baseName.similarTo(baseName)); // same object
        assertTrue(baseName.similarTo(new Name("Alice"))); // same spelling
        assertTrue(baseName.similarTo(new Name("alice"))); // all lowercase
        assertTrue(baseName.similarTo(new Name("ALICE"))); // all uppercase

        assertFalse(baseName.similarTo(new Name("Alice "))); // extra trailing whitespace
        assertFalse(baseName.similarTo(new Name("Alicee"))); // different spelling
    }
}
