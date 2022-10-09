package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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

        // same name, all other attributes different -> returns true
        Internship editedAlibaba =
                new InternshipBuilder(ALIBABA).withPhone(VALID_PHONE_TIKTOK).withEmail(VALID_EMAIL_TIKTOK)
                .withAddress(VALID_ADDRESS_TIKTOK).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALIBABA.isSameInternship(editedAlibaba));

        // different name, all other attributes same -> returns false
        editedAlibaba = new InternshipBuilder(ALIBABA).withName(VALID_NAME_TIKTOK).build();
        assertFalse(ALIBABA.isSameInternship(editedAlibaba));

        // name differs in case, all other attributes same -> returns false
        Internship editedTiktok = new InternshipBuilder(TIKTOK).withName(VALID_NAME_TIKTOK.toLowerCase()).build();
        assertFalse(TIKTOK.isSameInternship(editedTiktok));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_TIKTOK + " ";
        editedTiktok = new InternshipBuilder(TIKTOK).withName(nameWithTrailingSpaces).build();
        assertFalse(TIKTOK.isSameInternship(editedTiktok));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship aliceCopy = new InternshipBuilder(ALIBABA).build();
        assertTrue(ALIBABA.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALIBABA.equals(ALIBABA));

        // null -> returns false
        assertFalse(ALIBABA.equals(null));

        // different type -> returns false
        assertFalse(ALIBABA.equals(5));

        // different internship -> returns false
        assertFalse(ALIBABA.equals(TIKTOK));

        // different name -> returns false
        Internship editedAlice = new InternshipBuilder(ALIBABA).withName(VALID_NAME_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new InternshipBuilder(ALIBABA).withPhone(VALID_PHONE_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlice));

        // different email -> returns false
        editedAlice = new InternshipBuilder(ALIBABA).withEmail(VALID_EMAIL_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlice));

        // different address -> returns false
        editedAlice = new InternshipBuilder(ALIBABA).withAddress(VALID_ADDRESS_TIKTOK).build();
        assertFalse(ALIBABA.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternshipBuilder(ALIBABA).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALIBABA.equals(editedAlice));
    }
}
