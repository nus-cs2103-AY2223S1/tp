package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.NuScheduler;
import seedu.address.model.event.Attendees;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.Title;
import seedu.address.model.profile.Profile;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String title;
    private final String start;
    private final String end;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedProfile> attendees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("title") String title, @JsonProperty("start") String start,
                            @JsonProperty("end") String end, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("attendees") List<JsonAdaptedProfile> attendees) {
        this.title = title;
        this.start = start;
        this.end = end;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (attendees != null) {
            this.attendees.addAll(attendees);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        title = source.getTitle().title;
        start = source.getStartDateTime().toString();
        end = source.getEndDateTime().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attendees.addAll(source.getAttendees().stream()
                .map(JsonAdaptedProfile::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType(NuScheduler nuScheduler) throws IllegalValueException {
        final List<Tag> eventTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            eventTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }
        if (!DateTime.isValidDateTime(start)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelStart = new DateTime(start);

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }
        if (!DateTime.isValidDateTime(end)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelEnd = new DateTime(end);

        final Attendees modelAttendees = new Attendees();
        for (JsonAdaptedProfile attendee : attendees) {
            Profile p = attendee.toModelType();
            if (nuScheduler.hasProfile(p)) {
                int index = nuScheduler.getProfileList().indexOf(p);
                p = nuScheduler.getProfileList().get(index);
                modelAttendees.addProfile(p);
            }
        }

        final Set<Tag> modelTags = new HashSet<>(eventTags);

        if (modelStart.hasTime() != modelEnd.hasTime()) {
            throw new IllegalValueException(Messages.MESSAGE_EVENTS_HAS_TIME);
        }

        if (!modelStart.isBeforeOrEqual(modelEnd)) {
            throw new IllegalValueException(Messages.MESSAGE_EVENTS_INVALID_START_END);
        }

        return new Event(modelTitle, modelStart, modelEnd, modelTags, modelAttendees);
    }

}
