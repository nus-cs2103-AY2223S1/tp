package seedu.condonery.model.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.condonery.model.person.Name;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.condonery.model.person.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.condonery.model.person.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.condonery.model.person.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.condonery.model.person.Name.isValidName("")); // empty string
        assertFalse(seedu.condonery.model.person.Name.isValidName(" ")); // spaces only
        assertFalse(seedu.condonery.model.person.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.condonery.model.person.Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(seedu.condonery.model.person.Name.isValidName("peter jack")); // alphabets only
        assertTrue(seedu.condonery.model.person.Name.isValidName("12345")); // numbers only
        assertTrue(seedu.condonery.model.person.Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(seedu.condonery.model.person.Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
