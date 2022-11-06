package seedu.address.model.buyer;

import static java.util.Objects.requireNonNull;

import seedu.address.model.characteristics.Characteristics;

/**
 * Tests that a given {@code Buyer}'s {@code DesiredCharacteristics} contains any of the given characteristics.
 */
public class FilterBuyersContainingAnyCharacteristicPredicate extends AbstractFilterBuyersPredicate {

    private final Characteristics givenCharacteristics;

    /**
     * Standard constructor for the predicate.
     */
    public FilterBuyersContainingAnyCharacteristicPredicate(Characteristics characteristics) {
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
                || (other instanceof FilterBuyersContainingAnyCharacteristicPredicate // instanceof handles nulls
                && givenCharacteristics.equals((
                        (FilterBuyersContainingAnyCharacteristicPredicate) other).givenCharacteristics)); // state check
    }
}
