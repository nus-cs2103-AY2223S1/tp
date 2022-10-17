package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Medication;

/**
 * Jackson-friendly version of {@link Medication}.
 */
class JsonAdaptedMedication {

    private final String medicationName;

    /**
     * Constructs a {@code JsonAdaptedMedication} with the given {@code medicationName}.
     */
    @JsonCreator
    public JsonAdaptedMedication(String medicationName) {
        this.medicationName = medicationName;
    }

    /**
     * Converts a given {@code Medication} into this class for Jackson use.
     */
    public JsonAdaptedMedication(Medication source) {
        medicationName = source.medicationName;
    }

    @JsonValue
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * Converts this Jackson-friendly adapted medication object into the model's {@code Medication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Medication toModelType() throws IllegalValueException {
        if (!Medication.isValidMedication(medicationName)) {
            throw new IllegalValueException(Medication.MESSAGE_CONSTRAINTS);
        }
        return Medication.of(medicationName);
    }

}
