package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.FIONA;
import static seedu.address.testutil.TypicalProfiles.GEORGE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.profile.Profile;
import seedu.address.testutil.ProfileBuilder;

public class AttendeesTest {

    private final Attendees attendees = new Attendees();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendees(null));
    }

    @Test
    public void constructor_emptyList_returnsEmptyAttendees() {
        assertEquals(attendees, new Attendees(List.of()));
    }

    @Test
    public void getAttendeesList_emptyAttendees_returnsEmptyList() {
        assertEquals(List.of(), attendees.getAttendeesList());
    }

    @Test
    public void hasAttendee_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendees.hasAttendee(null));
    }

    @Test
    public void hasAttendee_profileNotInList_returnsFalse() {
        assertFalse(attendees.hasAttendee(FIONA));
    }

    @Test
    public void hasAttendee_profileInList_returnsTrue() {
        attendees.addProfile(FIONA);
        assertTrue(attendees.hasAttendee(FIONA));
    }

    @Test
    public void hasAttendee_profileWithSameIdentityFieldsInList_returnsFalse() {
        attendees.addProfile(FIONA);
        Profile editedFiona = new ProfileBuilder(FIONA).withTags(VALID_TAG_CCA).build();
        assertFalse(attendees.hasAttendee(editedFiona));
    }

    @Test
    public void addProfile_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendees.addProfile(null));
    }

    @Test
    public void addProfiles_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendees.addProfiles(null));
    }

    @Test
    public void removeAttendee_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendees.removeAttendee(null));
    }

    @Test
    public void removeAttendee_existingProfile_removesProfile() {
        attendees.addProfile(FIONA);
        attendees.removeAttendee(FIONA);
        Attendees expectedAttendees = new Attendees();
        assertEquals(expectedAttendees, attendees);
    }

    @Test
    public void removeAttendees_existingProfiles_removesProfiles() {
        attendees.addProfile(FIONA);
        attendees.addProfile(ALICE);
        attendees.removeAttendees(List.of(FIONA, ALICE));
        Attendees expectedAttendees = new Attendees();
        assertEquals(expectedAttendees, attendees);
    }

    @Test
    public void getAttendee_existingAttendee_success() {
        attendees.addProfile(FIONA);
        assertEquals(attendees.getAttendee(0), FIONA);
    }

    @Test
    public void getAttendee_indexOutOfBounds_throwsIndexOutOfBoundsException() {
        attendees.addProfile(FIONA);
        assertThrows(IndexOutOfBoundsException.class, () -> attendees.getAttendee(1));
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(attendees.isEmpty());
    }

    @Test
    public void isEmpty_withOneProfile_returnsFalse() {
        attendees.addProfile(FIONA);
        assertFalse(attendees.isEmpty());
    }

    @Test
    public void getAttendeesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> attendees.getAttendeesList().remove(0));
    }

    @Test
    public void addEventToAttendees_addNewEvent_eventAdded() {
        attendees.addProfile(FIONA);
        attendees.addProfile(GEORGE);
        assertFalse(FIONA.isAttendingEvent(PRESENTATION));
        assertFalse(GEORGE.isAttendingEvent(PRESENTATION));

        attendees.addEventToAttendees(PRESENTATION);
        assertTrue(FIONA.isAttendingEvent(PRESENTATION));
        assertTrue(GEORGE.isAttendingEvent(PRESENTATION));
    }

    @Test
    public void removeEventFromAttendees_removeExistingEvent_eventRemoved() {
        attendees.addProfile(FIONA);
        attendees.addProfile(GEORGE);
        attendees.addEventToAttendees(PRESENTATION);
        attendees.removeEventFromAttendees(PRESENTATION);

        assertFalse(FIONA.isAttendingEvent(PRESENTATION));
        assertFalse(GEORGE.isAttendingEvent(PRESENTATION));
    }


}
