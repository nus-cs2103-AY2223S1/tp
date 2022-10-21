package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
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

public class NuSchedulerTest {

    private final NuScheduler nuScheduler = new NuScheduler();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), nuScheduler.getProfileList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNuScheduler_replacesData() {
        NuScheduler newData = getTypicalNuScheduler();
        nuScheduler.resetData(newData);
        assertEquals(newData, nuScheduler);
    }

    @Test
    public void resetData_withDuplicateProfiles_throwsDuplicateProfileException() {
        // Two profiles with the same identity fields
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(ALICE, editedAlice);
        List<Event> emptyEvents = new ArrayList<>();
        NuSchedulerStub newData = new NuSchedulerStub(newProfiles, emptyEvents);
        assertThrows(DuplicateProfileException.class, () -> nuScheduler.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two profiles with the same identity fields
        Event editedPresentation = new EventBuilder(PRESENTATION)
                .withTags(VALID_TAG_CCA).build();
        List<Event> newEvents = Arrays.asList(PRESENTATION, editedPresentation);
        List<Profile> emptyProfiles = new ArrayList<>();
        NuSchedulerStub newData = new NuSchedulerStub(emptyProfiles, newEvents);
        assertThrows(DuplicateEventException.class, () -> nuScheduler.resetData(newData));
    }

    @Test
    public void hasProfile_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.hasProfile(null));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.hasEvent(null));
    }

    @Test
    public void hasProfile_profileNotInNuScheduler_returnsFalse() {
        assertFalse(nuScheduler.hasProfile(ALICE));
    }

    @Test
    public void hasEvent_eventNotInNuScheduler_returnsFalse() {
        assertFalse(nuScheduler.hasEvent(PRESENTATION));
    }

    @Test
    public void hasProfile_profileInNuScheduler_returnsTrue() {
        nuScheduler.addProfile(ALICE);
        assertTrue(nuScheduler.hasProfile(ALICE));
    }

    @Test
    public void hasEvent_eventInNuScheduler_returnsTrue() {
        nuScheduler.addEvent(PRESENTATION);
        assertTrue(nuScheduler.hasEvent(PRESENTATION));
    }

    @Test
    public void hasProfile_profileWithSameIdentityFieldsInNuScheduler_returnsTrue() {
        nuScheduler.addProfile(ALICE);
        Profile editedAlice = new ProfileBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(nuScheduler.hasProfile(editedAlice));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInNuScheduler_returnsTrue() {
        nuScheduler.addEvent(PRESENTATION);
        Event editedPresentation = new EventBuilder(PRESENTATION).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(nuScheduler.hasEvent(editedPresentation));
    }

    @Test
    public void getProfileList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> nuScheduler.getProfileList().remove(0));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> nuScheduler.getEventList().remove(0));
    }

    /**
     * A stub ReadOnlyNuScheduler whose profiles list can violate interface constraints.
     */
    private static class NuSchedulerStub implements ReadOnlyNuScheduler {
        private final ObservableList<Profile> profiles = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        NuSchedulerStub(Collection<Profile> profiles, Collection<Event> events) {
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
