package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationVenue;
import seedu.address.model.datetime.DatetimeRange;

/**
 * Jackson-friendly version of {@link Consultation}.
 */
public class JsonAdaptedConsultation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";

    private final String name;
    private final String module;
    private final String venue;
    private final String timeslot;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given Consultation details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("name") String name, @JsonProperty("module") String module,
                               @JsonProperty("venue") String venue, @JsonProperty("timeslot") String timeslot,
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
        module = source.getModule().moduleName;
        venue = source.getVenue().venue;
        timeslot = source.getTimeslot().toString();
        description = source.getDescription().description;
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
                    ConsultationModule.class.getSimpleName()));
        }
        if (!ConsultationModule.isValidModule(module)) {
            throw new IllegalValueException(ConsultationModule.MESSAGE_CONSTRAINTS);
        }
        final ConsultationModule modelModule = new ConsultationModule(module);

        if (venue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ConsultationVenue.class.getSimpleName()));
        }
        if (!ConsultationVenue.isValidVenue(venue)) {
            throw new IllegalValueException(ConsultationVenue.MESSAGE_CONSTRAINTS);
        }
        final ConsultationVenue modelVenue = new ConsultationVenue(venue);

//        if (timeslot == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
//                    ConsultationTimeslot.class.getSimpleName()));
//        }
//        if (!ConsultationTimeslot.isValidTimeslot(timeslot)) {
//            throw new IllegalValueException(ConsultationTimeslot.MESSAGE_CONSTRAINTS);
//        }
//        final ConsultationTimeslot modelTimeslot = new ConsultationTimeslot(timeslot);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ConsultationDescription.class.getSimpleName()));
        }
        if (!ConsultationDescription.isValidDescription(description)) {
            throw new IllegalValueException(ConsultationName.MESSAGE_CONSTRAINTS);
        }

        final ConsultationDescription modelDescription = new ConsultationDescription(description);

        DatetimeRange modelTimeslot = new DatetimeRange("fe", "fe");

        return new Consultation(modelName, modelModule, modelVenue, modelTimeslot, modelDescription);
    }

}
