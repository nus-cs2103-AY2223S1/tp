package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALIBABA;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.testutil.InternshipBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedAlibaba =
                new InternshipBuilder(ALIBABA).withAppliedDate(VALID_APPLIED_DATE_TIKTOK)
                        .withTags(VALID_TAG_BACKEND).build();
        List<Internship> newInternships = Arrays.asList(ALIBABA, editedAlibaba);
        AddressBookStub newData = new AddressBookStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasInternship(ALIBABA));
    }

    @Test
    public void hasInternship_internshipInAddressBook_returnsTrue() {
        addressBook.addInternship(ALIBABA);
        assertTrue(addressBook.hasInternship(ALIBABA));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addInternship(ALIBABA);
        Internship editedAlibaba = new InternshipBuilder(ALIBABA)
                .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).withTags(VALID_TAG_BACKEND).build();
        assertTrue(addressBook.hasInternship(editedAlibaba));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getInternshipList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose internships list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        AddressBookStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
