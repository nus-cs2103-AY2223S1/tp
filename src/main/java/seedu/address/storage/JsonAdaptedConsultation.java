package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.commons.Venue;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.datetime.DatetimeRange;
import seedu.address.storage.datetime.JsonAdaptedDatetimeRange;

/**
 * Jackson-friendly version of {@link Consultation}.
 */
public class JsonAdaptedConsultation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";

    private final String name;
    private final String module;
    private final String venue;
    private final String description;
    private final JsonAdaptedDatetimeRange timeslot;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given Consultation details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("name") String name, @JsonProperty("module") String module,
                               @JsonProperty("venue") String venue,
                               @JsonProperty("timeslot") JsonAdaptedDatetimeRange timeslot,
                               @JsonProperty("description") String description) {
        this.name = name;
        this.module = module;
        this.venue = venue;
        this.timeslot = timeslot;
        this.description = description;
    }

    /**
     * Converts a given {@code Consultation} into this class for Jackson use.
     */
    public JsonAdaptedConsultation(Consultation source) {
        name = source.getName().fullName;
        module = source.getModule().moduleCode;
        venue = source.getVenue().venue;
        description = source.getDescription().description;
        timeslot = new JsonAdaptedDatetimeRange(source.getTimeslot());
    }

    /**
     * Converts this Jackson-friendly adapted Consultation object into the model's {@code Consultation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Consultation.
     */
    public Consultation toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ConsultationName.class.getSimpleName()));
        }
        if (!ConsultationName.isValidName(name)) {
            throw new IllegalValueException(ConsultationName.MESSAGE_CONSTRAINTS);
        }

        final ConsultationName modelName = new ConsultationName(name);

        if (module == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModule(module)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModule = new ModuleCode(module);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Venue.class.getSimpleName()));
        }
        if (!Venue.isValidVenue(venue)) {
            throw new IllegalValueException(Venue.MESSAGE_CONSTRAINTS);
        }
        final Venue modelVenue = new Venue(venue);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ConsultationDescription.class.getSimpleName()));
        }
        if (!ConsultationDescription.isValidDescription(description)) {
            throw new IllegalValueException(ConsultationName.MESSAGE_CONSTRAINTS);
        }

        final ConsultationDescription modelDescription = new ConsultationDescription(description);

        final DatetimeRange datetimeRange = timeslot.toModelType();

        return new Consultation(modelName, modelModule, modelVenue, datetimeRange, modelDescription);
    }
}
