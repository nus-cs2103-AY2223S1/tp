package seedu.address.model.order.predicates;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.order.Order;

import java.util.function.Predicate;

/**
 * Tests that a {@code Order}'s {@code UniqueId} matches the Unique Id given.
 */
public class UniqueIdPredicate<T extends Order> implements Predicate<T> {
    private final UniqueId uniqueId;

    public UniqueIdPredicate(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public boolean test(T order) {
        String uniqueId = order.getId().getIdToString();
        boolean isOrder = order.getId().getIdToString().equals(uniqueId);
        return isOrder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueIdPredicate // instanceof handles nulls
                && uniqueId.equals(((UniqueIdPredicate) other).uniqueId)); // state check
    }
}
