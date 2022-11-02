package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
/**
 * Represents an Appointment's doctor in the HealthContact.
 * Guarantees: immutable; is valid as declared in {@link #isValidDoctorName (String)}
 */
public class Doctor {


    public static final String MESSAGE_CONSTRAINTS =
            "Doctor Names should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the doctor must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String doctorName;

    /**
     * Constructs a {@code Doctor}.
     *
     * @param doctorName A valid name of a doctor.
     */
    public Doctor(String doctorName) {
        requireNonNull(doctorName);
        checkArgument(isValidDoctorName(doctorName), MESSAGE_CONSTRAINTS);
        this.doctorName = doctorName;
    }

    /**
     * Returns true if a given string is a valid doctor name.
     */
    public static boolean isValidDoctorName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return doctorName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Doctor // instanceof handles nulls
                && doctorName.equals(((Doctor) other).doctorName)); // state check
    }

    @Override
    public int hashCode() {
        return doctorName.hashCode();
    }
}
