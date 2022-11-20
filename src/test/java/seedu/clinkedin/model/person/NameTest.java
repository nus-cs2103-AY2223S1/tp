package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

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
        String nameStr = "John Doe";
        Name name = new Name(nameStr);
        assertEquals(name.toString(), nameStr);
    }

    @Test
    public void equalityTests() {
        String noteStr1 = "This is a note";
        String noteStr2 = "This is a note";
        Note note1 = new Note(noteStr1);
        Note note2 = new Note(noteStr2);

        assertTrue(note1.equals(note1));
        assertTrue(note1.equals(note2));
        assertFalse(note1.equals(null));
        assertFalse(note1.equals(6));
    }

    @Test
    public void hashcodeTests() {
        String noteStr1 = "This is a note";
        String noteStr2 = "This is a note";
        int hashcode1 = noteStr1.hashCode();
        int hashcode2 = noteStr2.hashCode();
        assertEquals(hashcode1, hashcode2);
        assertEquals(hashcode1, hashcode1);
    }
}
