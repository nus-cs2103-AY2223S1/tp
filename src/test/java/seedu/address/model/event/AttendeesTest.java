package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.FIONA;

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
    public void asUnmodifiableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> attendees.getAttendeesList().remove(0));
    }
}
