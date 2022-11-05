package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
import static seedu.address.testutil.TypicalProfiles.AMY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.NuScheduler;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Title;

public class JsonAdaptedEventTest {
    private static final String INVALID_START = "+651234";
    private static final String INVALID_END = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final List<JsonAdaptedProfile> NON_EXISTENT_ATTENDEES = List.of(
            new JsonAdaptedProfile(AMY));

    private static final String VALID_TITLE = PRESENTATION.getTitle().toString();
    private static final String VALID_START = PRESENTATION.getStartDateTime().toString();
    private static final String VALID_END = PRESENTATION.getEndDateTime().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = PRESENTATION.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedProfile> VALID_ATTENDEES = PRESENTATION.getAttendees()
            .getAttendeesList().stream().map(JsonAdaptedProfile::new)
            .collect(Collectors.toList());
    private static final NuScheduler NUS_SCHEDULER = getTypicalNuScheduler();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(PRESENTATION);
        assertEquals(PRESENTATION, event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_START, VALID_END, VALID_TAGS, VALID_ATTENDEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_invalidStartDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_TITLE, INVALID_START, VALID_END, VALID_TAGS, VALID_ATTENDEES);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_nullStartDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_TITLE, null, VALID_END, VALID_TAGS, VALID_ATTENDEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_invalidEndDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_TITLE, VALID_START, INVALID_END, VALID_TAGS, VALID_ATTENDEES);
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_nullEndDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_TITLE, VALID_START, null, VALID_TAGS, VALID_ATTENDEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_TITLE, VALID_START, VALID_END, invalidTags, VALID_ATTENDEES);
        assertThrows(IllegalValueException.class, () -> event.toModelType(NUS_SCHEDULER));
    }

    @Test
    public void toModelType_nonExistentAttendees_returnsEventWithValidAttendees() {
        List<JsonAdaptedProfile> invalidProfiles = new ArrayList<>(VALID_ATTENDEES);
        invalidProfiles.addAll(NON_EXISTENT_ATTENDEES);
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_TITLE, VALID_START, VALID_END, VALID_TAGS,
                invalidProfiles);

        try {
            assertEquals(PRESENTATION, event.toModelType(NUS_SCHEDULER));
        } catch (IllegalValueException e) {
            throw new AssertionError("There should not be an error converting the event.", e);
        }

    }
}
