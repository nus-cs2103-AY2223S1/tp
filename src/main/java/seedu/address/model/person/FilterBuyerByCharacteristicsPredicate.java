package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.address.model.characteristics.Characteristics;

/**
 * Tests that a given {@code Person}'s {@code DesiredCharacteristics} contains the given characteristic.
 */
public class FilterBuyerByCharacteristicsPredicate extends AbstractFilterBuyerPredicate {

    private static final String CHARACTERISTIC_DELIMITER = ";";

    private final String[] givenCharacteristics;

    /**
     * Standard constructor for the predicate. characteristics must be delimited
     * by semicolons.
     */
    public FilterBuyerByCharacteristicsPredicate(String characteristics) {
        requireNonNull(characteristics);
        this.givenCharacteristics = characteristics.split(CHARACTERISTIC_DELIMITER);
    }

    @Override
    public boolean test(Person p) {
        // N.B.: Returns true if the target person does not have a DesiredCharacteristics object in their attributes.
        if (p.getDesiredCharacteristics().isEmpty()) {
            return true;
        }
        Characteristics dc = p.getDesiredCharacteristics().get();
        return Arrays.stream(givenCharacteristics).anyMatch(dc::containsCharacteristic);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBuyerByCharacteristicsPredicate // instanceof handles nulls
                && Arrays.equals(givenCharacteristics, (
                        (FilterBuyerByCharacteristicsPredicate) other).givenCharacteristics)); // state check
    }
}
