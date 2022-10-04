package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class PatientType {

    public static final String MESSAGE_CONSTRAINTS = "Patient Type can only be i(inpatient) or o(outpatient),"
            + " and should not be blank";

    public static final String DEPENDENCY_CONSTRAINTS = "Inpatient patients must have a hospital wing, "
            + "floor number and ward number. \n%1$s";

    public final PatientTypes value;

    /**
     * Constructs an {@code Hospital Wing}.
     *
     * @param patientType A valid hospital wing.
     */
    public PatientType(PatientTypes patientType) {
        requireNonNull(patientType);
        value = patientType;
    }

    public boolean isInpatient() {
        return value.equals(PatientTypes.INPATIENT);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientType // instanceof handles nulls
                && value.equals(((PatientType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
    Enum to Describe the type of patient
     */
    public enum PatientTypes {
        INPATIENT {
            @Override
            public String toString() {
                return "I";
            }
        },
        OUTPATIENT{
            @Override
            public String toString() {
                return "O";
            }
        }
    }
}
