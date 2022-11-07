package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.NuScheduler;
import seedu.address.model.ReadOnlyNuScheduler;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * An Immutable NuScheduler that is serializable to JSON format.
 */
@JsonRootName(value = "nuscheduler")
class JsonSerializableNuScheduler {

    public static final String MESSAGE_SIMILAR_EMAIL = "Profiles list contains similar email(s).";
    public static final String MESSAGE_SIMILAR_PHONE = "Profiles list contains similar phone(s).";
    public static final String MESSAGE_SIMILAR_TELEGRAM = "Profiles list contains similar telegram(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";

    private final List<JsonAdaptedProfile> profiles = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNuScheduler} with the given profiles and events.
     */
    @JsonCreator
    public JsonSerializableNuScheduler(@JsonProperty("profiles") List<JsonAdaptedProfile> profiles,
                                       @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.profiles.addAll(profiles);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyNuScheduler} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNuScheduler}.
     */
    public JsonSerializableNuScheduler(ReadOnlyNuScheduler source) {
        profiles.addAll(source.getProfileList().stream().map(JsonAdaptedProfile::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this NUScheduler into the model's {@code NuScheduler} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public NuScheduler toModelType() throws IllegalValueException {
        NuScheduler nuScheduler = new NuScheduler();
        for (JsonAdaptedProfile jsonAdaptedProfile : profiles) {
            Profile profile = jsonAdaptedProfile.toModelType();
            if (nuScheduler.hasEmail(profile)) {
                throw new IllegalValueException(MESSAGE_SIMILAR_EMAIL);
            }
            if (nuScheduler.hasPhone(profile)) {
                throw new IllegalValueException(MESSAGE_SIMILAR_PHONE);
            }
            if (nuScheduler.hasTelegram(profile)) {
                throw new IllegalValueException(MESSAGE_SIMILAR_TELEGRAM);
            }
            nuScheduler.addProfile(profile);
        }
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType(nuScheduler);
            if (nuScheduler.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            event.addToAllAttendees();
            nuScheduler.addEvent(event);
        }
        return nuScheduler;
    }

}
