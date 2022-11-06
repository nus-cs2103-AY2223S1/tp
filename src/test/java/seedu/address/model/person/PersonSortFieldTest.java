package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonSortFieldTest {

    @Test
    public void createSortField_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PersonSortField.createSortField((String) null));
    }

    @Test
    public void createSortField_invalidSortField_throwsIllegalArgumentException() {
        String invalidSortField = "x";
        assertThrows(IllegalArgumentException.class, () -> PersonSortField.createSortField(invalidSortField));
    }

    @Test
    public void createSortField_validSortField_returnsCorrectSortField() {
        String validSortField = "n";
        PersonSortField sortField = PersonSortField.createSortField(validSortField);
        assertEquals(sortField.getField(), PersonSortFieldType.NAME);
    }

    @Test
    public void sortByNoField_returnsCorrectSortField() {
        PersonSortField sortField = PersonSortField.sortByNoField();
        assertEquals(sortField.getField(), PersonSortFieldType.NO_FIELD);
    }

    @Test
    public void isValidSortField_validSortField_returnsTrue() {
        // Sort field name
        assertTrue(PersonSortField.isValidPersonSortField("n"));
        assertTrue(PersonSortField.isValidPersonSortField("N"));

        // Sort field gender
        assertTrue(PersonSortField.isValidPersonSortField("g"));
        assertTrue(PersonSortField.isValidPersonSortField("G"));

        // Sort field DOB
        assertTrue(PersonSortField.isValidPersonSortField("d"));
        assertTrue(PersonSortField.isValidPersonSortField("D"));
    }

    @Test
    public void isValidSortField_invalidSortField_returnsFalse() {
        // Null sort field
        assertThrows(NullPointerException.class, () -> PersonSortField.isValidPersonSortField(null));

        // Empty string
        assertFalse(PersonSortField.isValidPersonSortField(""));

        // Whitespace
        assertFalse(PersonSortField.isValidPersonSortField(" "));

        // Incorrect single letter
        assertFalse(PersonSortField.isValidPersonSortField("x"));

        // Incorrect multiple letters
        assertFalse(PersonSortField.isValidPersonSortField("xyz"));

        // Special characters
        assertFalse(PersonSortField.isValidPersonSortField("*"));
    }

}
