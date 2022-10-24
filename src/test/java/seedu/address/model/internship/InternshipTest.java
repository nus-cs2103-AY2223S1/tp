package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TIKTOK;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_TIME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ECOMMERCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALIBABA;
import static seedu.address.testutil.TypicalInternships.TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(ALIBABA.isSameInternship(ALIBABA));

        // null -> returns false
        assertFalse(ALIBABA.isSameInternship(null));

        // same company and tags, all other attributes different -> returns true
        Internship editedAlibaba =
                new InternshipBuilder(ALIBABA).withLink(VALID_LINK_TIKTOK).withDescription(VALID_DESCRIPTION_TIKTOK)
                .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).withTags(VALID_TAG_ECOMMERCE).build();
        assertTrue(ALIBABA.isSameInternship(editedAlibaba));

        // different company, all other attributes same -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withCompany(VALID_COMPANY_TIKTOK).build();
        assertFalse(ALIBABA.isSameInternship(editedAlibaba));

        // company differs in case, all other attributes same -> returns false
        Internship editedTiktok = new InternshipBuilder(TIKTOK).withCompany(VALID_COMPANY_TIKTOK.toLowerCase()).build();
        assertFalse(TIKTOK.isSameInternship(editedTiktok));

        // company has trailing spaces, all other attributes same -> returns false
        String companyWithTrailingSpaces = VALID_COMPANY_TIKTOK + " ";
        editedTiktok = new InternshipBuilder(TIKTOK).withCompany(companyWithTrailingSpaces).build();
        assertFalse(TIKTOK.isSameInternship(editedTiktok));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship alibabaCopy = new InternshipBuilder(ALIBABA).build();
        assertTrue(ALIBABA.equals(alibabaCopy));

        // same object -> returns true
        assertTrue(ALIBABA.equals(ALIBABA));

        // null -> returns false
        assertFalse(ALIBABA.equals(null));

        // different type -> returns false
        assertFalse(ALIBABA.equals(5));

        // different internship -> returns false
        assertFalse(ALIBABA.equals(TIKTOK));

        // different company -> returns false
        Internship editedAlibaba = new InternshipBuilder(ALIBABA).withCompany(VALID_COMPANY_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlibaba));

        // different link -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withLink(VALID_LINK_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlibaba));

        // different description -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withDescription(VALID_DESCRIPTION_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlibaba));

        // different applied date -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withAppliedDate(VALID_APPLIED_DATE_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlibaba));

        /* To fix
        // different interview date -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withInterviewDateTime(VALID_INTERVIEW_DATE_TIME_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlibaba));
         */

        // different tags -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withTags(VALID_TAG_FRONTEND).build();
        assertFalse(ALIBABA.equals(editedAlibaba));
    }
}
