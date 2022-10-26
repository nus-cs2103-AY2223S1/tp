package seedu.address.model.order.predicates;

import java.util.function.Predicate;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.order.Order;

/**
 * Tests that a {@code Order}'s {@code UniqueId} matches the Unique Id given.
 */
public class UniqueOrderIdPredicate<T extends Order> implements Predicate<T> {
    private final UniqueId uniqueId;

    public UniqueOrderIdPredicate(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public boolean test(T order) {
        String orderId = uniqueId.getIdToString();
        boolean isOrder = order.getId().getIdToString().equals(orderId);
        return isOrder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOrderIdPredicate // instanceof handles nulls
                && uniqueId.equals(((UniqueOrderIdPredicate) other).uniqueId)); // state check
    }
}
