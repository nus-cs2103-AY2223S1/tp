package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import seedu.address.model.characteristics.Characteristics;

/**
 * Tests that a given {@code Property}'s {@code Characteristics} any of the given characteristics.
 */
public class FilterPropsContainingAnyCharacteristicPredicate extends AbstractFilterPropsPredicate {

    private final Characteristics givenCharacteristics;

    /**
     * Standard constructor for the predicate.
     */
    public FilterPropsContainingAnyCharacteristicPredicate(Characteristics characteristics) {
        requireNonNull(characteristics);
        this.givenCharacteristics = characteristics;
    }

    @Override
    public boolean test(Property p) {
        // N.B.: Returns false if the target property does not have a Characteristics object in their attributes.
        if (p.getCharacteristics().isEmpty()) {
            return false;
        }
        return p.getCharacteristics().get().containsAnyGivenCharacteristic(givenCharacteristics);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropsContainingAnyCharacteristicPredicate // instanceof handles nulls
                && givenCharacteristics.equals((
                        (FilterPropsContainingAnyCharacteristicPredicate) other).givenCharacteristics)); // state check
    }
}
