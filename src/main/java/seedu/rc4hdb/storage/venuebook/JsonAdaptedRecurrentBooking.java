package seedu.rc4hdb.storage.venuebook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.storage.residentbook.JsonAdaptedResident;

/**
 * Jackson-friendly version of {@link RecurrentBooking}.
 */
public class JsonAdaptedRecurrentBooking implements JsonAdaptedBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private String venueName;
    private JsonAdaptedResident resident;
    private String hourPeriod;
    private String dayOfWeek;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given {@code venueName}, {@code resident}, {@code hourPeriod}
     * and {@code dayOfWeek}.
     */
    @JsonCreator
    public JsonAdaptedRecurrentBooking(@JsonProperty("venueName") String venueName,
                          @JsonProperty("resident") JsonAdaptedResident resident,
                          @JsonProperty("hourPeriod") String hourPeriod,
                          @JsonProperty("dayOfWeek") String dayOfWeek) {
        this.venueName = venueName;
        this.resident = resident;
        this.hourPeriod = hourPeriod;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedRecurrentBooking(RecurrentBooking source) {
        venueName = source.getVenueName().value;
        resident = new JsonAdaptedResident(source.getResident());
        hourPeriod = source.getHourPeriod().value;
        dayOfWeek = source.getDayOfWeek().value;
    }

    /**
     * Converts this Jackson-friendly adapted recurrent booking object into the model's {@code RecurrentBooking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public RecurrentBooking toModelType() throws IllegalValueException {
        throwIfNullField(venueName, VenueName.class);
        if (!VenueName.isValidVenueName(venueName)) {
            throw new IllegalValueException(VenueName.MESSAGE_CONSTRAINTS);
        }
        final VenueName modelVenueName = new VenueName(venueName);

        final Resident modelResident = resident.toModelType();

        throwIfNullField(hourPeriod, HourPeriod.class);
        if (!HourPeriod.isValidHourPeriod(hourPeriod)) {
            throw new IllegalValueException(HourPeriod.MESSAGE_CONSTRAINTS);
        }
        final HourPeriod modelHourPeriod = new HourPeriod(hourPeriod);

        throwIfNullField(dayOfWeek, Day.class);
        if (!Day.isValidDay(dayOfWeek)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDayOfWeek = new Day(dayOfWeek);

        return new RecurrentBooking(modelVenueName, modelResident, modelHourPeriod, modelDayOfWeek);
    }

    /**
     * Throws an {@code IllegalValueException} if field is null.
     * @param field The field to check if null.
     * @param fieldClass The {@code Class} that field is supposed to be converted into.
     * @throws IllegalValueException if field is null.
     */
    private void throwIfNullField(String field, Class<?> fieldClass) throws IllegalValueException {
        if (field == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, fieldClass.getSimpleName()));
        }
    }



}
