package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import seedu.address.model.characteristics.Characteristics;

/**
 * Tests that a given {@code Property}'s {@code DesiredCharacteristics} contains the given characteristic.
 */
public class FilterPropsByCharacteristicsPredicate extends AbstractFilterPropsPredicate {

    private final Characteristics givenCharacteristics;

    /**
     * Standard constructor for the predicate.
     */
    public FilterPropsByCharacteristicsPredicate(Characteristics characteristics) {
        requireNonNull(characteristics);
        this.givenCharacteristics = characteristics;
    }

    @Override
    public boolean test(Property p) {
        // N.B.: Returns false if the target property does not have a Characteristics object in their attributes.
        if (p.getCharacteristics().isEmpty()) {
            return false;
        }
        return p.getCharacteristics().get().containsAllGivenCharacteristics(givenCharacteristics);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropsByCharacteristicsPredicate // instanceof handles nulls
                && givenCharacteristics.equals((
                        (FilterPropsByCharacteristicsPredicate) other).givenCharacteristics)); // state check
    }
}
