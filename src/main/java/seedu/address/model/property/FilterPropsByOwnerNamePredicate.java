package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import seedu.address.model.buyer.Name;

/**
 * Tests that a {@code Property}'s {@code Owner} matches the given owner's name.
 */
public class FilterPropsByOwnerNamePredicate extends AbstractFilterPropsPredicate {
    private final Name ownerName;

    /**
     * Standard constructor for the predicate.
     */
    public FilterPropsByOwnerNamePredicate(Name ownerName) {
        requireNonNull(ownerName);
        this.ownerName = ownerName;
    }

    @Override
    public boolean test(Property p) {
        String curName = p.getOwnerName().fullName.toLowerCase();
        return curName.contains(ownerName.fullName.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropsByOwnerNamePredicate // instanceof handles nulls
                && ownerName.equals((
                (FilterPropsByOwnerNamePredicate) other).ownerName)); // state check
    }
}
