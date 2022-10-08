package seedu.address.model.person.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalTutors.TUTOR1;
import static seedu.address.testutil.TypicalTutors.TUTOR2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorBuilder;

public class TutorTest {

    @Test
    public void equals() {
        // same values -> returns true
        Tutor tutor1Copy = new TutorBuilder(TUTOR1).build();
        assertTrue(TUTOR1.equals(tutor1Copy));

        // same object -> returns true
        assertTrue(TUTOR1.equals(TUTOR1));

        // null -> returns false
        assertFalse(TUTOR1.equals(null));

        // different type -> returns false
        assertFalse(TUTOR1.equals(5));

        // different tutor -> returns false
        assertFalse(TUTOR1.equals(TUTOR2));

        // different name -> returns false
        Tutor editedTutor1 = new TutorBuilder(TUTOR1).withName(VALID_NAME_BOB).build();
        assertFalse(TUTOR1.equals(editedTutor1));

        // different phone -> returns false
        editedTutor1 = new TutorBuilder(TUTOR1).withPhone(VALID_PHONE_BOB).build();
        assertFalse(TUTOR1.equals(editedTutor1));

        // different email -> returns false
        editedTutor1 = new TutorBuilder(TUTOR1).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(TUTOR1.equals(editedTutor1));

        // different address -> returns false
        editedTutor1 = new TutorBuilder(TUTOR1).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(TUTOR1.equals(editedTutor1));

        // different tags -> returns false
        editedTutor1 = new TutorBuilder(TUTOR1).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(TUTOR1.equals(editedTutor1));

        // different qualification -> returns false
        editedTutor1 = new TutorBuilder(TUTOR1).withQualification("Bachelor of Clowning").build();
        assertFalse(TUTOR1.equals(editedTutor1));

        // different institution -> returns false
        editedTutor1 = new TutorBuilder(TUTOR1).withInstitution("Clowning University").build();
        assertFalse(TUTOR1.equals(editedTutor1));
    }
}
