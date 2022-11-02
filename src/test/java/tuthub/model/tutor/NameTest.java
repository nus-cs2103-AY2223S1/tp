package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("peter12")); // contains numbers
        assertFalse(Name.isValidName("jones@smith")); // contains symbols that are not allowed
        assertFalse(Name.isValidName("jones smith@henry")); // contains symbols that are not allowed
        assertFalse(Name.isValidName("jones'/smith")); // contains 2 symbols together
        assertFalse(Name.isValidName(" henry")); // spaces in front
        assertFalse(Name.isValidName("henry ")); // spaces behind
        assertFalse(Name.isValidName("Smith - Jones")); // space between symbols and letters


        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr")); // long names
        assertTrue(Name.isValidName("Harry O'Neil")); // apostrophes
        assertTrue(Name.isValidName("Smith-Jones")); // hyphens
        assertTrue(Name.isValidName("Smith-Jones Henry")); // hyphens with more words
        assertTrue(Name.isValidName("O'Neil Peter")); // apostrophes with more words
        assertTrue(Name.isValidName("Rohan s/o Samar")); // slashes with more words

    }
}
