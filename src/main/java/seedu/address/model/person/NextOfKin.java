package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's Next of Kin in the Patient Database.
 * Guarantees: immutable; is valid as declared in {@link #isValidNextOfKin(String)}
 */
public class NextOfKin {

    public static final String MESSAGE_CONSTRAINTS = "Next of Kin should not be blank "
            + "and should come in the format: Name, Relationship, Contact";

    public static final String STRING_VALIDATION_REGEX = "[A-Za-z\\s]+";
    public static final String INTEGER_VALIDATION_REGEX = "[0-9\\s]+";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param nextOfKin A valid address.
     */
    public NextOfKin(String nextOfKin) {
        requireNonNull(nextOfKin);
        checkArgument(isValidNextOfKin(nextOfKin), MESSAGE_CONSTRAINTS);
        value = nextOfKin;
    }

    /**
     * Returns true if a given string is a valid Next of Kin.
     */
    public static boolean isValidNextOfKin(String test) {
        String[] nextOfKinData = test.split(",", 3);
        if (nextOfKinData.length < 3) {
            return false;
        }
        String name = nextOfKinData[0].trim();
        String relationship = nextOfKinData[1].trim();
        String contact = nextOfKinData[2].trim();
        Boolean isValidName = !name.equals("") && name.matches(STRING_VALIDATION_REGEX);
        Boolean isValidRelationship = !relationship.equals("") && relationship.matches(STRING_VALIDATION_REGEX);
        Boolean isValidContact = !contact.equals("") && contact.matches(INTEGER_VALIDATION_REGEX);
        return isValidContact && isValidRelationship && isValidName;
    }

    @Override
    public String toString() {
        return "Next of Kin: " + value;
    }

    public String getNextOfKinName() {
        return value.split(",", 3)[0].trim();
    }

    public String getNextOfKinRelationship() {
        return value.split(",", 3)[1].trim();
    }

    public String getNextOfKinContact() {
        return value.split(",", 3)[2].trim();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextOfKin // instanceof handles nulls
                && value.equals(((NextOfKin) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
