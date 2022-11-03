package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRACTICE;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.BENSON;
import static seedu.address.testutil.TypicalProfiles.BOB;
import static seedu.address.testutil.TypicalProfiles.FIONA;
import static seedu.address.testutil.TypicalProfiles.GEORGE;

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
import seedu.address.model.profile.exceptions.SimilarProfileException;
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
    public void resetData_withSimilarEmails_throwsSimilarProfileException() {
        // Two profiles with the similar email
        Profile bobWithAmyEmail = new ProfileBuilder(BOB).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(AMY, bobWithAmyEmail);
        List<Event> emptyEvents = new ArrayList<>();
        NuSchedulerStub newData = new NuSchedulerStub(newProfiles, emptyEvents);

        assertThrows(SimilarProfileException.class, () -> nuScheduler.resetData(newData));
    }

    @Test
    public void resetData_withSimilarPhones_throwsSimilarProfileException() {
        // Two profiles with the similar phone
        Profile bobWithAmyPhone = new ProfileBuilder(BOB).withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Profile> newProfiles = Arrays.asList(AMY, bobWithAmyPhone);
        List<Event> emptyEvents = new ArrayList<>();
        NuSchedulerStub newData = new NuSchedulerStub(newProfiles, emptyEvents);

        assertThrows(SimilarProfileException.class, () -> nuScheduler.resetData(newData));
    }

    @Test
    public void resetData_withSimilarTelegrams_throwsSimilarProfileException() {
        // Two profiles with the similar telegram
        Profile bobWithAmyTelegram = new ProfileBuilder(BOB).withTelegram(VALID_TELEGRAM_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        List<Profile> newProfiles = Arrays.asList(AMY, bobWithAmyTelegram);
        List<Event> emptyEvents = new ArrayList<>();
        NuSchedulerStub newData = new NuSchedulerStub(newProfiles, emptyEvents);

        assertThrows(SimilarProfileException.class, () -> nuScheduler.resetData(newData));
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
    public void hasEmail_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.hasEmail(null));
    }

    @Test
    public void hasPhone_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.hasPhone(null));
    }

    @Test
    public void hasTelegram_nullTelegram_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.hasTelegram(null));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nuScheduler.hasEvent(null));
    }

    @Test
    public void hasEmail_emailNotInAddressBook_returnsFalse() {
        assertFalse(nuScheduler.hasEmail(ALICE));
    }

    @Test
    public void hasEmail_emailInAddressBook_returnsTrue() {
        nuScheduler.addProfile(ALICE);
        assertTrue(nuScheduler.hasEmail(ALICE));
    }

    @Test
    public void hasPhone_phoneNotInAddressBook_returnsFalse() {
        assertFalse(nuScheduler.hasPhone(ALICE));
    }

    @Test
    public void hasPhone_phoneInAddressBook_returnsTrue() {
        nuScheduler.addProfile(ALICE);
        assertTrue(nuScheduler.hasPhone(ALICE));
    }

    @Test
    public void hasTelegram_telegramNotInAddressBook_returnsFalse() {
        assertFalse(nuScheduler.hasTelegram(ALICE));
    }

    @Test
    public void hasTelegram_telegramInAddressBook_returnsTrue() {
        nuScheduler.addProfile(ALICE);
        assertTrue(nuScheduler.hasTelegram(ALICE));
    }

    @Test
    public void hasEvent_eventNotInNuScheduler_returnsFalse() {
        assertFalse(nuScheduler.hasEvent(PRESENTATION));
    }

    @Test
    public void hasEvent_eventInNuScheduler_returnsTrue() {
        nuScheduler.addEvent(PRESENTATION);
        assertTrue(nuScheduler.hasEvent(PRESENTATION));
    }

    @Test
    public void hasProfile_profileWithSameEmailFieldsInNuScheduler_returnsTrue() {
        nuScheduler.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withEmail(VALID_EMAIL_AMY)
                .build();
        assertTrue(nuScheduler.hasEmail(editedBenson));
    }

    @Test
    public void hasProfile_profileWithSamePhoneFieldsInNuScheduler_returnsTrue() {
        nuScheduler.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withPhone(VALID_PHONE_AMY)
                .build();
        assertTrue(nuScheduler.hasPhone(editedBenson));
    }

    @Test
    public void hasProfile_profileWithSameTelegramFieldsInNuScheduler_returnsTrue() {
        nuScheduler.addProfile(AMY);
        Profile editedBenson = new ProfileBuilder(BENSON).withTelegram(VALID_TELEGRAM_AMY)
                .build();
        assertTrue(nuScheduler.hasTelegram(editedBenson));
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

    @Test
    public void addEventAttendees_addNewProfiles_profilesAdded() {
        nuScheduler.addEvent(PRESENTATION);
        nuScheduler.addProfile(FIONA);
        nuScheduler.addProfile(GEORGE);
        assertFalse(PRESENTATION.hasAttendee(FIONA));
        assertFalse(PRESENTATION.hasAttendee(GEORGE));

        nuScheduler.addEventAttendees(PRESENTATION, List.of(FIONA, GEORGE));
        assertTrue(PRESENTATION.hasAttendee(FIONA));
        assertTrue(PRESENTATION.hasAttendee(GEORGE));
    }

    @Test
    public void addEventToAttendees_addNewEvent_eventAdded() {
        nuScheduler.addProfile(FIONA);
        nuScheduler.addProfile(GEORGE);
        nuScheduler.addEvent(PRACTICE);
        assertFalse(FIONA.isAttendingEvent(PRACTICE));
        assertFalse(GEORGE.isAttendingEvent(PRACTICE));

        nuScheduler.addEventToAttendees(PRACTICE, List.of(FIONA, GEORGE));
        assertTrue(FIONA.isAttendingEvent(PRACTICE));
        assertTrue(GEORGE.isAttendingEvent(PRACTICE));
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
