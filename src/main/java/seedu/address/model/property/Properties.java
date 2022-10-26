package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.address.Address;

/**
 * Represents a list of properties that a seller is selling / managing.
 */
public class Properties {

    public static final String MESSAGE_CONSTRAINTS = "A list of properties must be represented by the"
            + "relative indices of the properties in the list, separated by spaces. A property list can be empty.";

    /*
     * Digits that represent relative indices must be separated by spaces.
     */
    public static final String VALIDATION_REGEX = "(^(\\d\\s?)*$)";

    // TODO propertyList is not populated at all (linking).
    public final List<Property> propertyList = new ArrayList<>();
    public final String[] indicesArray;

    /**
     * Constructs a {@code Properties}.
     * Guarantees: Immutable, is valid as declared in isValidPropertiesString.
     */
    public Properties(String spaceSeparatedProperties) {
        requireNonNull(spaceSeparatedProperties);
        checkArgument(isValidPropertiesString(spaceSeparatedProperties), MESSAGE_CONSTRAINTS);
        indicesArray = spaceSeparatedProperties.split(" ");
    }

    public List<Property> getPropertyList() {
        return this.propertyList;
    }

    /**
     * Returns true if a given string is valid to be used in desired characteristics.
     */
    public static boolean isValidPropertiesString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Properties // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    // TODO update toString method with proper implementation of List<Property> once linked
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Properties Owned: ");
        sb.append(Arrays.toString(indicesArray));
        return sb.toString();
    }
}
