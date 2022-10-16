package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalInternships.ALICE;
import static seedu.workbook.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.workbook.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(ALICE.isSameInternship(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameInternship(null));

        // same company, all other attributes different -> returns true
        Internship editedAlice = new InternshipBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameInternship(editedAlice));

        // different company, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE).withCompany(VALID_COMPANY_BOB).build();
        assertFalse(ALICE.isSameInternship(editedAlice));

        // company differs in case, all other attributes same -> returns false
        Internship editedBob = new InternshipBuilder(BOB).withCompany(VALID_COMPANY_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameInternship(editedBob));

        // company has trailing spaces, all other attributes same -> returns false
        String companyWithTrailingSpaces = VALID_COMPANY_BOB + " ";
        editedBob = new InternshipBuilder(BOB).withCompany(companyWithTrailingSpaces).build();
        assertFalse(BOB.isSameInternship(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship aliceCopy = new InternshipBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different internship -> returns false
        assertFalse(ALICE.equals(BOB));

        // different company -> returns false
        Internship editedAlice = new InternshipBuilder(ALICE).withCompany(VALID_COMPANY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));


        // different email -> returns false
        editedAlice = new InternshipBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
