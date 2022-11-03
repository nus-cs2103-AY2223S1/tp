package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Name} contains the given string.
 */
public class PropertyNameContainsSubstringPredicate implements Predicate<Property> {
    private final String string;

    public PropertyNameContainsSubstringPredicate(String string) {
        this.string = string.toLowerCase();
    }

    @Override
    public boolean test(Property property) {
        String name = property.getPropertyName().fullName.toLowerCase();
        return name.contains(string);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyNameContainsSubstringPredicate // instanceof handles nulls
                && string.equals(((PropertyNameContainsSubstringPredicate) other).string)); // state check
    }
}

