package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Medication;

/**
 * Jackson-friendly version of {@link Medication}.
 */
class JsonAdaptedMedication {

    private final String medicationName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
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
    public String getTagName() {
        return medicationName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Medication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Medication toModelType() throws IllegalValueException {
        if (!Medication.isValidMedicationName(medicationName)) {
            throw new IllegalValueException(Medication.MESSAGE_CONSTRAINTS);
        }
        return new Medication(medicationName);
    }

}
