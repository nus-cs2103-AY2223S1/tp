package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private final String reason;
    private final String dateTime;
    private final String period;
    private final boolean isMarked;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("reason") String reason,
                                  @JsonProperty("dateTime") String dateTime,
                                  @JsonProperty("period") String period,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                  @JsonProperty("isMarked") boolean isMarked) {
        this.reason = reason;
        this.dateTime = dateTime;
        this.period = period;
        this.isMarked = isMarked;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        this.reason = source.getReason();
        this.dateTime = source.getDateTime().format(Appointment.STORAGE_FORMATTER);
        this.period = source.getFormattedPeriod();
        this.isMarked = source.isMarked();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        final List<Tag> appointmentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            appointmentTags.add(tag.toModelType());
        }

        if (!Appointment.isValidReason(reason)) {
            throw new IllegalValueException(Appointment.REASON_MESSAGE_CONSTRAINTS);
        }
        if (!Appointment.isValidDateTime(dateTime)) {
            throw new IllegalValueException(Appointment.DATE_MESSAGE_CONSTRAINTS);
        }

        if (!Appointment.isValidTimePeriod(period)) {
            throw new IllegalValueException(Appointment.TIME_PERIOD_MESSAGE_CONSTRAINTS);
        }
        final Set<Tag> modelTags = new HashSet<>(appointmentTags);

        return new Appointment(reason, dateTime, period, modelTags, isMarked);
    }
}
