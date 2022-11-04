package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.tag.Medication;

/**
 * Jackson-friendly version of {@link PastAppointment}.
 */
public class JsonAdaptedPastAppointment {
    public static final String APPT_MISSING_FIELD_MESSAGE_FORMAT = "PastAppointment's %s field is missing!";
    private final String pastAppointmentDate;
    private final String pastAppointmentDiagnosis;
    private final List<JsonAdaptedMedication> pastAppointmentMedication = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPastAppointment} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPastAppointment(@JsonProperty("date") String date,
                                      @JsonProperty("diagnosis") String diagnosis,
                                      @JsonProperty("medication") List<JsonAdaptedMedication> medication) {
        this.pastAppointmentDate = date;
        this.pastAppointmentDiagnosis = diagnosis;
        if (medication != null) {
            this.pastAppointmentMedication.addAll(medication);
        }
    }

    /**
     * Converts a given {@code PastAppointment} into this class for Jackson use.
     */
    public JsonAdaptedPastAppointment(PastAppointment source) {
        this.pastAppointmentDate = source.getDate().format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        this.pastAppointmentDiagnosis = source.getDiagnosis();
        this.pastAppointmentMedication.addAll(source.getMedication().stream()
                .map(JsonAdaptedMedication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code PastAppointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public PastAppointment toModelType() throws IllegalValueException {
        final List<Medication> personMedications = new ArrayList<>();
        for (JsonAdaptedMedication medication : pastAppointmentMedication) {
            personMedications.add(medication.toModelType());
        }
        if (pastAppointmentDate == null) {
            throw new IllegalValueException(String.format(APPT_MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }
        final LocalDate modelPastAppointmentDate = LocalDate.parse(pastAppointmentDate,
                DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        if (pastAppointmentMedication == null) {
            throw new IllegalValueException(String.format(APPT_MISSING_FIELD_MESSAGE_FORMAT,
                    Medication.class.getSimpleName()));
        }
        final Set<Medication> modelMedication = new HashSet<>(personMedications);
        final String modelDiagnosis = pastAppointmentDiagnosis;

        return new PastAppointment(modelPastAppointmentDate, modelMedication, modelDiagnosis);
    }
}
