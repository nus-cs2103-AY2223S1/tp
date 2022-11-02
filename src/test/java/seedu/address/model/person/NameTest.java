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
    public void equals() {
        final Name standardName = new Name("Pam Beesly");

        // same values -> returns true
        Name sameName = new Name("Pam Beesly");
        assertTrue(standardName.equals(sameName));

        // same object -> returns true
        assertTrue(standardName.equals(standardName));

        // null -> returns false
        assertFalse(standardName.equals(null));

        // different types -> returns false
        assertFalse(standardName.equals(new Phone("94445555")));

        // different names -> returns false
        assertFalse(standardName.equals(new Name("Kevin Malone")));
    }

    @Test
    public void hashcode() {
        final Name standardName = new Name("Pam Beesly");

        // same values -> returns same hashcode
        Name sameName = new Name("Pam Beesly");
        assertEquals(standardName.hashCode(), sameName.hashCode());

        // different names -> returns different hashcode
        assertNotEquals(standardName.hashCode(), (new Name("Kevin Malone")).hashCode());
    }

}
