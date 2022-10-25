package seedu.address.model.buyer;

import static java.util.Objects.requireNonNull;

import seedu.address.model.characteristics.Characteristics;

/**
 * Tests that a given {@code Buyer}'s {@code DesiredCharacteristics} contains the given characteristic.
 */
public class FilterBuyerByCharacteristicsPredicate extends AbstractFilterBuyerPredicate {

    private final Characteristics givenCharacteristics;

    /**
     * Standard constructor for the predicate.
     */
    public FilterBuyerByCharacteristicsPredicate(Characteristics characteristics) {
        requireNonNull(characteristics);
        this.givenCharacteristics = characteristics;
    }

    @Override
    public boolean test(Buyer p) {
        // N.B.: Returns true if the target buyer does not have a DesiredCharacteristics object in their attributes.
        if (p.getDesiredCharacteristics().isEmpty()) {
            return false;
        }
        return p.getDesiredCharacteristics().get().containsAllGivenCharacteristics(givenCharacteristics);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBuyerByCharacteristicsPredicate // instanceof handles nulls
                && givenCharacteristics.equals((
                        (FilterBuyerByCharacteristicsPredicate) other).givenCharacteristics)); // state check
    }
}
