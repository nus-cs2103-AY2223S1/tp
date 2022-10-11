package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.getTypicalApplicationBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.testutil.ApplicationBuilder;

public class ApplicationBookTest {

    private final ApplicationBook applicationBook = new ApplicationBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), applicationBook.getApplicationList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> applicationBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyApplicationBook_replacesData() {
        ApplicationBook newData = getTypicalApplicationBook();
        applicationBook.resetData(newData);
        assertEquals(newData, applicationBook);
    }

    @Test
    public void resetData_withDuplicateApplications_throwsDuplicateApplicationException() {
        // Two Applications with the same identity fields
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withEmail(VALID_EMAIL_GOOGLE)
                .withDate(VALID_DATE_FACEBOOK).build();
        List<Application> newApplications = Arrays.asList(BYTEDANCE, editedBytedance);
        ApplicationBookStub newData = new ApplicationBookStub(newApplications);

        assertThrows(DuplicateApplicationException.class, () -> applicationBook.resetData(newData));
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> applicationBook.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInApplicationBook_returnsFalse() {
        assertFalse(applicationBook.hasApplication(BYTEDANCE));
    }

    @Test
    public void hasApplication_applicationInApplicationBook_returnsTrue() {
        applicationBook.addApplication(BYTEDANCE);
        assertTrue(applicationBook.hasApplication(BYTEDANCE));
    }

    @Test
    public void hasApplication_applicationWithSameIdentityFieldsInApplicationBook_returnsTrue() {
        applicationBook.addApplication(BYTEDANCE);
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withEmail(VALID_EMAIL_GOOGLE)
                .withDate(VALID_DATE_FACEBOOK).build();
        assertTrue(applicationBook.hasApplication(editedBytedance));
    }

    @Test
    public void hasApplication_applicationWithDifferentPositionFieldInApplicationBook_returnsTrue() {
        //Same Company different Position
        applicationBook.addApplication(BYTEDANCE);
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withPosition(VALID_POSITION_GOOGLE).build();
        assertFalse(applicationBook.hasApplication(editedBytedance));
    }

    @Test
    public void hasApplication_applicationWithDifferentCompanyFieldInApplicationBook_returnsTrue() {
        //Same Position different Company
        applicationBook.addApplication(BYTEDANCE);
        Application editedBytedance = new ApplicationBuilder(BYTEDANCE).withCompany(VALID_COMPANY_FACEBOOK).build();
        assertFalse(applicationBook.hasApplication(editedBytedance));
    }

    @Test
    public void getApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> applicationBook.getApplicationList().remove(0));
    }

    /**
     * A stub ReadOnlyApplicationBook whose Applications list can violate interface constraints.
     */
    private static class ApplicationBookStub implements ReadOnlyApplicationBook {
        private final ObservableList<Application> applications = FXCollections.observableArrayList();

        ApplicationBookStub(Collection<Application> Applications) {
            this.applications.setAll(Applications);
        }

        @Override
        public ObservableList<Application> getApplicationList() {
            return applications;
        }
    }

}
