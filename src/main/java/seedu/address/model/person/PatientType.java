package seedu.address.model.person;

import java.util.Locale;

import static java.util.Objects.requireNonNull;

public class PatientType {

    public static final String MESSAGE_CONSTRAINTS = "Patient type can only be i(inpatient) or o(outpatient),"
            + " and should not be blank";

    public static final String DEPENDENCY_CONSTRAINTS = "Only inpatient patients can and must have a hospital wing, "
            + "floor number and ward number. \n%1$s";

    public final PatientTypes value;

    /**
     * Constructs an {@code Hospital Wing}.
     *
     * @param patientType A valid patient type.
     */
    public PatientType(PatientTypes patientType) {
        requireNonNull(patientType);
        value = patientType;
    }

    /**
     * Returns true if a given string is a valid Patient Type.
     */
    public static boolean isValidPatientType(String test) {
        return PatientTypes.parsePatientType(test) != null;
    }

    public boolean isInpatient() {
        return value.equals(PatientTypes.INPATIENT);
    }

    @Override
    public String toString() {
        return "Patient Type: " + value.name();
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
        };

        /**
         * Returns an {@code PatientTypes} enum.
         *
         * @param strPatientType the string patient type to be parsed.
         */
        public static PatientTypes parsePatientType(String strPatientType) {
            String capsPatientType = strPatientType.toUpperCase(Locale.ROOT);
            PatientTypes[] patientTypes = PatientTypes.values();
            for (PatientTypes pt : patientTypes) {
                if (capsPatientType.equals(pt.name()) || capsPatientType.equals(pt.toString())) {
                    return pt;
                }
            }
            return null;
        }
    }
}
