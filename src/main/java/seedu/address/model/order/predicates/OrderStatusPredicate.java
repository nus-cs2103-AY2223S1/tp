package seedu.address.model.order.predicates;

import java.util.function.Predicate;

import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;

public class OrderStatusPredicate<T extends Order> implements Predicate<T> {
    private final OrderStatus orderStatus;

    public OrderStatusPredicate(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean test(T order) {
        return order.getOrderStatus().equals(orderStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderStatusPredicate // instanceof handles nulls
                && orderStatus.equals(((OrderStatusPredicate) other).orderStatus)); // state check
    }
}
