package seedu.address.model.attribute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private static final Field DEFAULT_FIELD = new Field("Github", "johndoe123");
    private static final Field DEFAULT_EMPTY_FIELD = new Field("Telegram");

    @Test
    void constructor_validInputs_success() {
        Field field = new Field("Github", "johndoe123");
        assertEquals(field, DEFAULT_FIELD);
    }

    @Test
    void constructor_nameOnly_success() {
        Field field = new Field("Telegram");
        assertEquals(field, DEFAULT_EMPTY_FIELD);
    }

    @Test
    void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Field("Github", null));
    }

    @Test
    void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Field(null, "johndoe123"));
        assertThrows(NullPointerException.class, () -> new Field(null));
    }

    @Test
    void setValue_validInput_success() {
        assertEquals(DEFAULT_FIELD.setValue("abc"), new Field("Github", "abc"));
    }

    @Test
    void setValue_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DEFAULT_FIELD.setValue(null));
    }

    @Test
    void isValueSet_emptyValue_returnsTrue() {
        assertTrue(DEFAULT_EMPTY_FIELD.isValueSet());
    }

    @Test
    void isValueSet_nonEmptyValue_returnsFalse() {
        assertFalse(DEFAULT_FIELD.isValueSet());
    }

    @Test
    void isValidField_validName_returnsTrue() {
        assertTrue(Field.isValidField("abcd"));
    }

    @Test
    void isValidField_invalidName_returnsFalse() {
        assertFalse(Field.isValidField("  abcd"));
    }

    @Test
    void isNameMatch_sameName_returnsTrue() {
        assertTrue(DEFAULT_FIELD.isNameMatch("Github"));
    }

    @Test
    void isNameMatch_differentName_returnsFalse() {
        assertFalse(DEFAULT_FIELD.isNameMatch("Hubgit"));
    }

    @Test
    void isNameMatch_sameNameDifferentCase_returnsTrue() {
        assertTrue(DEFAULT_FIELD.isNameMatch("giTHUB"));
    }

    @Test
    void getValue_success() {
        assertEquals(DEFAULT_FIELD.getValue(), "johndoe123");
    }

    @Test
    void toString_success() {
        assertEquals(DEFAULT_FIELD.toString(), "[Github,johndoe123]");
        assertEquals(DEFAULT_EMPTY_FIELD.toString(), "[Telegram,null]");
    }

    @Test
    void testEquals() {
        // Same object -> returns true
        assertTrue(DEFAULT_FIELD.equals(DEFAULT_FIELD));

        // Same type and value -> returns true
        assertTrue(DEFAULT_FIELD.equals(new Field("Github", "johndoe123")));

        // Case-insensitive same type and value -> returns true
        assertTrue(DEFAULT_FIELD.equals(new Field("gitHUB", "johndoe123")));

        // Same type different value -> returns false
        assertFalse(DEFAULT_FIELD.equals(new Field("Github", "johndoe")));
        assertFalse(DEFAULT_FIELD.equals(DEFAULT_EMPTY_FIELD));

        // Different type same value -> returns false
        assertFalse(DEFAULT_FIELD.equals(new Field("Githubb", "johndoe123")));

        // Same type same value, but case-sensitive value -> returns false
        assertFalse(DEFAULT_FIELD.equals(new Field("Github", "Johndoe123")));

    }

    @Test
    void testHashCode() {
        assertEquals(DEFAULT_FIELD.hashCode(), 258744195);
        assertEquals(DEFAULT_EMPTY_FIELD.hashCode(), -1295823583);
    }
}