package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalProfiles.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.exceptions.DuplicateProfileException;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ProfileBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getProfileList());
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
    public void resetData_withDuplicateProfiles_throwsDuplicateProfileException() {
        // Two profiles with the same identity fields
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(ALICE, editedAlice);
        List<Event> emptyEvents = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(newProfiles, emptyEvents);
        assertThrows(DuplicateProfileException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two profiles with the same identity fields
        Event editedPresentation = new EventBuilder(PRESENTATION)
                .withTags(VALID_TAG_CCA).build();
        List<Event> newEvents = Arrays.asList(PRESENTATION, editedPresentation);
        List<Profile> emptyProfiles = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(emptyProfiles, newEvents);
        assertThrows(DuplicateEventException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasProfile_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProfile(null));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEvent(null));
    }

    @Test
    public void hasProfile_profileNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasProfile(ALICE));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEvent(PRESENTATION));
    }

    @Test
    public void hasProfile_profileInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        assertTrue(addressBook.hasProfile(ALICE));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        addressBook.addEvent(PRESENTATION);
        assertTrue(addressBook.hasEvent(PRESENTATION));
    }

    @Test
    public void hasProfile_profileWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addProfile(ALICE);
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasProfile(editedAlice));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addEvent(PRESENTATION);
        Event editedPresentation = new EventBuilder(PRESENTATION).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasEvent(editedPresentation));
    }

    @Test
    public void getProfileList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProfileList().remove(0));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getEventList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose profiles list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Profile> profiles = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        AddressBookStub(Collection<Profile> profiles, Collection<Event> events) {
            this.profiles.setAll(profiles);
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Profile> getProfileList() {
            return profiles;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }

    }

}
