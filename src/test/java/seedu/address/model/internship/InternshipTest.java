package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_STATUS_BOBBY;
import static seedu.address.testutil.TypicalInternships.ABC;
import static seedu.address.testutil.TypicalInternships.BOBBY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(ABC.isSameInternship(ABC));

        // null -> returns false
        assertFalse(ABC.isSameInternship(null));

        // same name and role, all other attributes different -> returns true
        Internship editedAbc = new InternshipBuilder(ABC)
                .withStatus(VALID_STATUS_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY).build();
        assertTrue(ABC.isSameInternship(editedAbc));

        // different name, all other attributes same -> returns false
        editedAbc = new InternshipBuilder(ABC).withCompanyName(VALID_COMPANY_NAME_BOBBY).build();
        assertFalse(ABC.isSameInternship(editedAbc));

        // name differs in case, all other attributes same -> returns false
        Internship editedBob = new InternshipBuilder(BOBBY)
                .withCompanyName(VALID_COMPANY_NAME_BOBBY.toLowerCase()).build();
        assertFalse(BOBBY.isSameInternship(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_NAME_BOBBY + " ";
        editedBob = new InternshipBuilder(BOBBY).withCompanyName(nameWithTrailingSpaces).build();
        assertFalse(BOBBY.isSameInternship(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship abcCopy = new InternshipBuilder(ABC).build();
        assertTrue(ABC.equals(abcCopy));

        // same object -> returns true
        assertTrue(ABC.equals(ABC));

        // null -> returns false
        assertFalse(ABC.equals(null));

        // different type -> returns false
        assertFalse(ABC.equals(5));

        // different internship -> returns false
        assertFalse(ABC.equals(BOBBY));

        // different name -> returns false
        Internship editedAbc = new InternshipBuilder(ABC).withCompanyName(VALID_COMPANY_NAME_BOBBY).build();
        assertFalse(ABC.equals(editedAbc));

        // different role -> returns false
        editedAbc = new InternshipBuilder(ABC).withRole(VALID_ROLE_BOBBY).build();
        assertFalse(ABC.equals(editedAbc));

        // different status -> returns false
        editedAbc = new InternshipBuilder(ABC).withStatus(VALID_STATUS_BOBBY).build();
        assertFalse(ABC.equals(editedAbc));

        // different interview date -> returns false
        editedAbc = new InternshipBuilder(ABC).withInterviewDate(VALID_INTERVIEW_BOBBY).build();
        assertFalse(ABC.equals(editedAbc));

        // different InternshipId -> returns false
        editedAbc = new InternshipBuilder(ABC).withInternshipId(1).build();
        assertFalse(ABC.equals(editedAbc));
    }

    @Test
    public void testGetRole() {
        Internship abc = new InternshipBuilder(ABC).build();
        InternshipRole role = abc.getInternshipRole();
        InternshipRole expected = new InternshipRole(VALID_ROLE_ABC);
        assertEquals(expected, role);
    }

    @Test
    public void testGetStatus() {
        Internship abc = new InternshipBuilder(ABC).build();
        InternshipStatus status = abc.getInternshipStatus();
        InternshipStatus expected = new InternshipStatus(InternshipStatus.State.PENDING);
        assertEquals(expected, status);
    }

    @Test
    public void testGetInterviewDate() {
        Internship abc = new InternshipBuilder(ABC).build();
        InterviewDate interviewDate = abc.getInterviewDate();
        InterviewDate expected = new InterviewDate(VALID_INTERVIEW_ABC);
        assertEquals(expected, interviewDate);
    }

}
