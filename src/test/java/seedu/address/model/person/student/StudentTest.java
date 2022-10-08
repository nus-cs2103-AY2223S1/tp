package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void equals() {
        // same values -> returns true
        Student student1Copy = new StudentBuilder(STUDENT1).build();
        assertTrue(STUDENT1.equals(student1Copy));

        // same object -> returns true
        assertTrue(STUDENT1.equals(STUDENT1));

        // null -> returns false
        assertFalse(STUDENT1.equals(null));

        // different type -> returns false
        assertFalse(STUDENT1.equals(5));

        // different tutor -> returns false
        assertFalse(STUDENT1.equals(STUDENT2));

        // different name -> returns false
        Student editedStudent1 = new StudentBuilder(STUDENT1).withName(VALID_NAME_BOB).build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different phone -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withPhone(VALID_PHONE_BOB).build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different email -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different address -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different tags -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different school -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withSchool("Random Primary School").build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different level -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withLevel("SECONDARY1").build();
        assertFalse(STUDENT1.equals(editedStudent1));

        // different next of kin -> returns false
        editedStudent1 = new StudentBuilder(STUDENT1).withNextOfKin("Jean Zin").build();
        assertFalse(STUDENT1.equals(editedStudent1));
    }
}
