package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class DateOfBirthTest {
    @Test
    public void constructor_emptyString_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> DateOfBirth.parseString(""));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateOfBirth.parseString(null));
    }

    @Test
    public void constructor_properInput() {
        try {
            DateOfBirth dob = DateOfBirth.parseString("2020-10-10");
            assert true;
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void equals_sameObject() {
        try {
            DateOfBirth dob = DateOfBirth.parseString("2020-10-10");
            assertEquals(dob, dob);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void equals_differentObject() {
        try {
            DateOfBirth dob1 = DateOfBirth.parseString("2020-10-10");
            DateOfBirth dob2 = DateOfBirth.parseString("2020-10-10");
            assertEquals(dob1, dob2);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void toString_differentObjects() {
        try {
            DateOfBirth dob1 = DateOfBirth.parseString("2020-10-10");
            DateOfBirth dob2 = DateOfBirth.parseString("2020-10-10");
            assertEquals(dob1.toString(), dob2.toString());
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void hashCode_differentObjects() {
        try {
            DateOfBirth dob1 = DateOfBirth.parseString("2020-10-10");
            DateOfBirth dob2 = DateOfBirth.parseString("2020-10-10");
            assertEquals(dob1.hashCode(), dob2.hashCode());
        } catch (IllegalValueException e) {
            assert false;
        }
    }
}
