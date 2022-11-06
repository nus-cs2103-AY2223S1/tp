package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_DATETIME_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_STAGE_BOB;
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

        // different role same stage, returns false
        Internship editedAlice = new InternshipBuilder(ALICE).withRole(VALID_ROLE_BOB).build();
        assertFalse(ALICE.isSameInternship(editedAlice));

        // different company same stage, returns false
        Internship editedAliceCompanyOnly = new InternshipBuilder(ALICE).withCompany(VALID_COMPANY_BOB).build();
        assertFalse(ALICE.isSameInternship(editedAliceCompanyOnly));

        // same company and role, different stage -> returns true as company and role are defining factors of
        // the same internship application
        Internship editedAliceCompanyAndRole = new InternshipBuilder(ALICE).withStage(VALID_STAGE_BOB).build();
        assertTrue(ALICE.isSameInternship(editedAliceCompanyAndRole));

        // company differs in case, all other attributes same -> returns true
        Internship editedBob = new InternshipBuilder(BOB).withCompany(VALID_COMPANY_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameInternship(editedBob));

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

        // different stage -> returns false
        editedAlice = new InternshipBuilder(ALICE).withStage(VALID_STAGE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different role -> returns false
        editedAlice = new InternshipBuilder(ALICE).withRole(VALID_ROLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different datetime -> returns false
        editedAlice = new InternshipBuilder(ALICE).withDateTime(VALID_DATETIME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(new InternshipBuilder(ALICE).build().hashCode(), new InternshipBuilder(ALICE).build().hashCode());
    }
}
