package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void toStringTest() {
        String expectedMessage1 = "John Doe";
        String expectedMessage2 = "Jane Doe";
        Name name1 = new Name("John Doe");
        Name name2 = new Name("Jane Doe");

        // same name
        assertEquals(expectedMessage1, name1.toString());
        assertEquals(expectedMessage2, name2.toString());

        // different name
        assertNotEquals(expectedMessage1, name2.toString());
        assertNotEquals(expectedMessage2, name1.toString());
    }

    @Test
    public void equals() {
        Name name = new Name("John Doe");

        // same object -> returns true
        assertTrue(name.equals(name));

        // same values -> returns true
        Name nameCopy = new Name("John Doe");
        assertTrue(name.equals(nameCopy));

        // different types -> returns false
        assertFalse(name.equals(1));
        assertFalse(name.equals("John Doe"));

        // null -> returns false
        assertFalse(name.equals(null));

        // different name -> returns false
        Name differentName = new Name("Jane Doe");
        assertFalse(name.equals(differentName));
    }

    @Test
    public void hashCodeTest() {
        Name name1 = new Name("John Doe");
        Name name2 = new Name("John Doe");
        Name name3 = new Name("Jane Doe");

        // same object -> returns same hashcode
        assertEquals(name1.hashCode(), name1.hashCode());

        // same values -> returns same hashcode
        assertEquals(name1.hashCode(), name2.hashCode());

        // different name -> returns different hashcode
        assertNotEquals(name1.hashCode(), name3.hashCode());
        assertNotEquals(name2.hashCode(), name3.hashCode());
    }
}
