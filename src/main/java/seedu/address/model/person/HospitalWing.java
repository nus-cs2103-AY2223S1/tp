package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents an inpatient's currently located hospital wing in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidHospitalWing(String)}
 */
public class HospitalWing {

    public static final String MESSAGE_CONSTRAINTS = "Hospital Wing is: "
            + "north, south, east or west only (case-insensitive) "
            + "for inpatient patients and blank for outpatient patients";

    public final String value;

    /**
     * Enum for Hospital wing type
     */
    public enum HospitalWingTypes {
        NORTH {
            @Override
            public String toString() {
                return "North";
            }
        },
        SOUTH {
            @Override
            public String toString() {
                return "South";
            }
        },
        EAST {
            @Override
            public String toString() {
                return "East";
            }
        },
        WEST {
            @Override
            public String toString() {
                return "West";
            }
        }
    }

    /**
     * Constructs an {@code Hospital Wing}.
     *
     * @param hospitalWing A valid hospital wing.
     */
    public HospitalWing(String hospitalWing) {
        requireNonNull(hospitalWing);
        checkArgument(isValidHospitalWing(hospitalWing), MESSAGE_CONSTRAINTS);
        // convert to title case
        String storedValue = hospitalWing.substring(0, 1).toUpperCase()
                + hospitalWing.substring(1).toLowerCase();
        value = storedValue;
    }

    /**
     * Returns true if a given string is a valid hospital wing.
     */
    public static boolean isValidHospitalWing(String test) {
        return Arrays.stream(HospitalWingTypes.values()).anyMatch(wingType ->
            test.toUpperCase().equals(wingType.name()));
    }

    @Override
    public String toString() {
        return "Hospital Wing: " + value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HospitalWing // instanceof handles nulls
                && value.equals(((HospitalWing) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

