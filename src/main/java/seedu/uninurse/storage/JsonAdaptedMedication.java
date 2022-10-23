package seedu.uninurse.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.medication.Medication;

/**
 * Jackson-friendly version of {@link Medication}.
 */
public class JsonAdaptedMedication {
    private final String medicationType;
    private final String dosage;

    /**
     * Constructs a {@code JsonAdaptedMedication} with the given {@code Medication}.
     */
    @JsonCreator
    public JsonAdaptedMedication(String medicationType, String dosage) {
        requireNonNull(medicationType);
        requireNonNull(dosage);
        this.medicationType = medicationType;
        this.dosage = dosage;
    }

    /**
     * Converts a given {@code Medication} into this class for Jackson use.
     */
    public JsonAdaptedMedication(Medication source) {
        requireNonNull(source);
        this.medicationType = source.getType();
        this.dosage = source.getDosage();
    }

    @JsonValue
    public String getType() {
        return medicationType;
    }

    @JsonValue
    public String getDosage() {
        return dosage;
    }

    /**
     * Converts this Jackson-friendly adapted medication object into the model's {@code Medication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medication.
     */
    public Medication toModelType() throws IllegalValueException {
        if (!Medication.isValidMedication(medicationType, dosage)) {
            throw new IllegalValueException(Medication.MESSAGE_CONSTRAINTS);
        }
        return new Medication(medicationType, dosage);
    }
}
