package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DATE_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_EMAIL_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_POSITION_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_PREFERRED;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalApplications.FACEBOOK;
import static seedu.application.testutil.TypicalApplications.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBuilder;

public class ApplicationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Application application = new ApplicationBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> application.getTags().remove(0));
    }

    @Test
    public void isSameApplication() {
        // same object -> returns true
        assertTrue(GOOGLE.isSameApplication(GOOGLE));

        // null -> returns false
        assertFalse(GOOGLE.isSameApplication(null));

        // same company and position, all other attributes different -> returns true
        Application editedGoogle = new ApplicationBuilder(GOOGLE).withContact(VALID_CONTACT_FACEBOOK)
                .withEmail(VALID_EMAIL_FACEBOOK).withDate(VALID_DATE_FACEBOOK).withTags(VALID_TAG_PREFERRED).build();
        assertTrue(GOOGLE.isSameApplication(editedGoogle));

        // different company, all other attributes same -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withCompany(VALID_COMPANY_FACEBOOK).build();
        assertFalse(GOOGLE.isSameApplication(editedGoogle));

        // different position, all other attributes same -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withPosition(VALID_POSITION_FACEBOOK).build();
        assertFalse(GOOGLE.isSameApplication(editedGoogle));

        // company differs in case, all other attributes same -> returns true
        Application editedFacebook = new ApplicationBuilder(FACEBOOK)
                .withCompany(VALID_COMPANY_FACEBOOK.toLowerCase()).build();
        assertTrue(FACEBOOK.isSameApplication(editedFacebook));

        // position differs in case, all other attributes same -> returns true
        editedFacebook = new ApplicationBuilder(FACEBOOK)
                .withPosition(VALID_POSITION_FACEBOOK.toLowerCase()).build();
        assertTrue(FACEBOOK.isSameApplication(editedFacebook));

        // company has trailing spaces, all other attributes same -> returns false
        String companyWithTrailingSpaces = VALID_COMPANY_FACEBOOK + " ";
        editedFacebook = new ApplicationBuilder(FACEBOOK).withCompany(companyWithTrailingSpaces).build();
        assertFalse(FACEBOOK.isSameApplication(editedFacebook));

        // position has trailing spaces, all other attributes same -> returns false
        String positionWithTrailingSpaces = VALID_POSITION_FACEBOOK + " ";
        editedFacebook = new ApplicationBuilder(FACEBOOK).withPosition(positionWithTrailingSpaces).build();
        assertFalse(FACEBOOK.isSameApplication(editedFacebook));
    }

    @Test
    public void hasSameArchivedStatus() {
        // same object -> returns true
        assertTrue(GOOGLE.hasSameArchiveStatus(GOOGLE));

        // null -> returns false
        assertFalse(GOOGLE.hasSameArchiveStatus(null));

        // different archived status, other attributes same -> returns false
        Application editedGoogle = new ApplicationBuilder(GOOGLE).withArchiveStatus(true).build();
        assertFalse(GOOGLE.hasSameArchiveStatus(editedGoogle));

        // two different application, same archived status -> returns true
        assertTrue(GOOGLE.hasSameArchiveStatus(FACEBOOK));

        // two different application, different archived status -> returns false
        Application editedFacebook = new ApplicationBuilder(FACEBOOK).withArchiveStatus(true).build();
        assertFalse(GOOGLE.hasSameArchiveStatus(editedGoogle));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Application googleCopy = new ApplicationBuilder(GOOGLE).build();
        assertEquals(GOOGLE, googleCopy);

        // same object -> returns true
        assertEquals(GOOGLE, GOOGLE);

        // null -> returns false
        assertNotEquals(null, GOOGLE);

        // different type -> returns false
        assertNotEquals(5, GOOGLE);

        // different application -> returns false
        assertNotEquals(GOOGLE, FACEBOOK);

        // different company -> returns false
        Application editedGoogle = new ApplicationBuilder(GOOGLE).withCompany(VALID_COMPANY_FACEBOOK).build();
        assertNotEquals(GOOGLE, editedGoogle);

        // different contact -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withContact(VALID_CONTACT_FACEBOOK).build();
        assertNotEquals(GOOGLE, editedGoogle);

        // different email -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withEmail(VALID_EMAIL_FACEBOOK).build();
        assertNotEquals(GOOGLE, editedGoogle);

        // different position -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withPosition(VALID_POSITION_FACEBOOK).build();
        assertNotEquals(GOOGLE, editedGoogle);

        // different date -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withDate(VALID_DATE_FACEBOOK).build();
        assertNotEquals(GOOGLE, editedGoogle);

        // different tags -> returns false
        editedGoogle = new ApplicationBuilder(GOOGLE).withTags(VALID_TAG_PREFERRED).build();
        assertFalse(GOOGLE.equals(editedGoogle));
    }
}
