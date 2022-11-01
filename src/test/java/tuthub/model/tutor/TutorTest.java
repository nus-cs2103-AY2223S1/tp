package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.BOB;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class TutorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Tutor tutor = new TutorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> tutor.getTags().remove(0));
    }

    @Test
    public void isSameTutor() {
        // same object -> returns true
        assertTrue(ALICE.isSameTutor(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTutor(null));

        // same name, all other attributes different -> returns false
        Tutor editedAlice = new TutorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withStudentId(VALID_STUDENTID_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameTutor(editedAlice));

        // different name, all other attributes same -> returns true
        editedAlice = new TutorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameTutor(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Tutor editedBob = new TutorBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameTutor(editedBob));

        // different email, all other attributes (including Student ID) same -> returns true
        editedAlice = new TutorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSameTutor(editedAlice));

        // different Student ID, all other attributes (including email) same -> returns true
        editedAlice = new TutorBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        assertTrue(ALICE.isSameTutor(editedAlice));

        // same name, email and Student ID, all other attributes different -> returns true
        editedAlice = new TutorBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTutor(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Tutor aliceCopy = new TutorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different tutor -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Tutor editedAlice = new TutorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new TutorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new TutorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
