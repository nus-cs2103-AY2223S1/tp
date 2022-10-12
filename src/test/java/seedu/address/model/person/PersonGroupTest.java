package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonGroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonGroup(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPersonGroup = " ";
        assertThrows(IllegalArgumentException.class, () -> new PersonGroup(invalidPersonGroup));
    }

    @Test
    public void isValidPersonGroupTest() {
        // null PersonGroup
        assertThrows(NullPointerException.class, () -> PersonGroup.isValidGroup(null));

        // invalid PersonGroup
        assertFalse(PersonGroup.isValidGroup("")); // empty string
        assertFalse(PersonGroup.isValidGroup("   ")); // spaces only

        // valid PersonGroup
        assertTrue(PersonGroup.isValidGroup("CS2103T")); // normal group
    }

    @Test
    public void equalityCheck() {
        PersonGroup personGroup1 = new PersonGroup("CS2103");
        PersonGroup personGroup2 = new PersonGroup("CS2103");
        PersonGroup personGroup3 = new PersonGroup("NTU");
        assertFalse(personGroup1 == personGroup2);
        assertTrue(personGroup1.equals(personGroup2));
        assertFalse(personGroup3.equals(personGroup2));
        assertTrue(personGroup1.toString().equals(personGroup2.getGroupName()));
        assertFalse(personGroup3.toString().equals(personGroup2.getGroupName()));
    }
}
