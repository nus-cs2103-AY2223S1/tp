package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Tests that a {@code Person}'s {@code priority} contains the given priority value.
 */
public class FilterBuyerByPriorityPredicate extends AbstractFilterBuyerPredicate {

    private final Priority priority;

    /**
     * Standard constructor for the predicate.
     */
    public FilterBuyerByPriorityPredicate(Priority priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    @Override
    public boolean test(Person p) {
        return p.getPriority().equals(priority);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBuyerByPriorityPredicate // instanceof handles nulls
                && priority.equals(((FilterBuyerByPriorityPredicate) other).priority)); // state check
    }

}
