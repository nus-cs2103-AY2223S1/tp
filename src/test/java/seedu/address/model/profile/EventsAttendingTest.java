package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRACTICE;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.BOB;
import static seedu.address.testutil.TypicalProfiles.HOON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

class EventsAttendingTest {

    private final EventsAttending eventsAttending = new EventsAttending();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventsAttending(null));
    }

    @Test
    public void constructor_emptyList_returnsEmptyEventsAttending() {
        assertEquals(eventsAttending, new EventsAttending(List.of()));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventsAttending.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInList_returnsFalse() {
        assertFalse(eventsAttending.hasEvent(PRESENTATION));
    }

    @Test
    public void hasEvent_eventInList_returnsTrue() {
        eventsAttending.addEvent(PRESENTATION);
        assertTrue(eventsAttending.hasEvent(PRESENTATION));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInList_returnsFalse() {
        eventsAttending.addEvent(PRESENTATION);
        Event editedPresentation = new EventBuilder(PRESENTATION).withAttendees(ALICE, BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(eventsAttending.hasEvent(editedPresentation));
    }

    @Test
    public void addEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventsAttending.addEvent(null));
    }

    @Test
    public void removeEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventsAttending.removeEvent(null));
    }

    @Test
    public void removeEvent_existingEvent_removesEvent() {
        eventsAttending.addEvent(PRESENTATION);
        eventsAttending.removeEvent(PRESENTATION);
        EventsAttending expectedEventsAttending = new EventsAttending();
        assertEquals(expectedEventsAttending, eventsAttending);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(eventsAttending.isEmpty());
    }

    @Test
    public void isEmpty_withOneProfile_returnsFalse() {
        eventsAttending.addEvent(PRESENTATION);
        assertFalse(eventsAttending.isEmpty());
    }

    @Test
    public void getEventsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> eventsAttending.getEventsList().remove(0));
    }

    @Test
    public void addAttendeeToEvents_newAttendee_attendeeAdded() {
        eventsAttending.addEvent(PRESENTATION);
        eventsAttending.addEvent(PRACTICE);
        assertFalse(PRESENTATION.hasAttendee(HOON));
        assertFalse(PRACTICE.hasAttendee(HOON));

        eventsAttending.addAttendeeToEvents(HOON);
        assertTrue(PRESENTATION.hasAttendee(HOON));
        assertTrue(PRESENTATION.hasAttendee(HOON));
    }
}
