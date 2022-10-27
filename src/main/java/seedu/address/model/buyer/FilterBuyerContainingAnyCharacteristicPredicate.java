package seedu.address.model.buyer;

import static java.util.Objects.requireNonNull;

import seedu.address.model.characteristics.Characteristics;

/**
 * Tests that a given {@code Buyer}'s {@code DesiredCharacteristics} contains any of the given characteristics.
 */
public class FilterBuyerContainingAnyCharacteristicPredicate extends AbstractFilterBuyerPredicate {

    private final Characteristics givenCharacteristics;

    /**
     * Standard constructor for the predicate.
     */
    public FilterBuyerContainingAnyCharacteristicPredicate(Characteristics characteristics) {
        requireNonNull(characteristics);
        this.givenCharacteristics = characteristics;
    }

    @Override
    public boolean test(Buyer p) {
        // N.B.: Returns false if the target buyer does not have a DesiredCharacteristics object in their attributes.
        if (p.getDesiredCharacteristics().isEmpty()) {
            return false;
        }
        return p.getDesiredCharacteristics().get().containsAnyGivenCharacteristic(givenCharacteristics);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBuyerContainingAnyCharacteristicPredicate // instanceof handles nulls
                && givenCharacteristics.equals((
                        (FilterBuyerContainingAnyCharacteristicPredicate) other).givenCharacteristics)); // state check
    }
}
