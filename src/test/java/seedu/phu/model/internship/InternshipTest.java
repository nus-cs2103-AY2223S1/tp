package seedu.phu.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.BLACKROCK;

import org.junit.jupiter.api.Test;

import seedu.phu.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(AMAZON.isSameInternship(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.isSameInternship(null));

        // same name, all other attributes different -> returns true
        Internship editedAmazon = new InternshipBuilder(AMAZON).withPhone(VALID_PHONE_BLACKROCK)
                .withEmail(VALID_EMAIL_BLACKROCK).withRemark(VALID_REMARK_BLACKROCK)
                .withTags(VALID_TAG_TRANSPORT).build();
        assertTrue(AMAZON.isSameInternship(editedAmazon));

        // different name, all other attributes same -> returns false
        editedAmazon = new InternshipBuilder(AMAZON).withName(VALID_NAME_BLACKROCK).build();
        assertFalse(AMAZON.isSameInternship(editedAmazon));

        // name differs in case, all other attributes same -> returns false
        Internship editedBlackrock = new InternshipBuilder(BLACKROCK)
                .withName(VALID_NAME_BLACKROCK.toLowerCase()).build();
        assertFalse(BLACKROCK.isSameInternship(editedBlackrock));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BLACKROCK + " ";
        editedBlackrock = new InternshipBuilder(BLACKROCK).withName(nameWithTrailingSpaces).build();
        assertFalse(BLACKROCK.isSameInternship(editedBlackrock));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship amazonCopy = new InternshipBuilder(AMAZON).build();
        assertTrue(AMAZON.equals(amazonCopy));

        // same object -> returns true
        assertTrue(AMAZON.equals(AMAZON));

        // null -> returns false
        assertFalse(AMAZON.equals(null));

        // different type -> returns false
        assertFalse(AMAZON.equals(5));

        // different internship -> returns false
        assertFalse(AMAZON.equals(BLACKROCK));

        // different name -> returns false
        Internship editedAmazon = new InternshipBuilder(AMAZON).withName(VALID_NAME_BLACKROCK).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different phone -> returns false
        editedAmazon = new InternshipBuilder(AMAZON).withPhone(VALID_PHONE_BLACKROCK).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different email -> returns false
        editedAmazon = new InternshipBuilder(AMAZON).withEmail(VALID_EMAIL_BLACKROCK).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different internship -> returns false
        editedAmazon = new InternshipBuilder(AMAZON).withRemark(VALID_REMARK_BLACKROCK).build();
        assertFalse(AMAZON.equals(editedAmazon));

        // different tags -> returns false
        editedAmazon = new InternshipBuilder(AMAZON).withTags(VALID_TAG_TRANSPORT).build();
        assertFalse(AMAZON.equals(editedAmazon));
    }
}
