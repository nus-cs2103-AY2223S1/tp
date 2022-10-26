package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import seedu.address.commons.util.StringUtil;

/**
 * Represents an inpatient's currently located hospital wing in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidHospitalWing(String)}
 */
public class HospitalWing {

    public static final String MESSAGE_CONSTRAINTS = "Hospital Wing should be of the following values "
            + "(east, west, north, south; case-insensitive)"
            + " and spaces for inpatients "
            + "and blank for outpatients";

    public final String value;

    /**
     * Enum for Hospital wing type
     */
    public enum HospitalWingTypes {
        SOUTH {
            @Override
            public String toString() {
                return "S";
            }
        },
        NORTH {
            @Override
            public String toString() {
                return "N";
            }
        },
        EAST {
            @Override
            public String toString() {
                return "E";
            }
        },
        WEST {
            @Override
            public String toString() {
                return "W";
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
        value = hospitalWing;
    }

    /**
     * Returns true if a given string is a valid hospital wing.
     */
    public static boolean isValidHospitalWing(String test) {
        String[] enumArr = Arrays.stream(HospitalWingTypes.values())
                .map(HospitalWingTypes::name).toArray(String[]::new);
        return StringUtil.containsWordIgnoreCase(String.join(" ", enumArr), test);
    }

    @Override
    public String toString() {
        return "Wing: " + value;
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

