package tracko.model.order;

import java.util.List;
import java.util.function.Predicate;

public class OrderDeliveredOrPaidPredicate implements Predicate<Order> {
    Boolean isPaid;
    Boolean isDelivered;

    public OrderDeliveredOrPaidPredicate(Boolean isPaid, Boolean isDelivered) {
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
    }

    @Override
    public boolean test(Order order) {
        return paidStatusMatch(order) && deliveredStatusMatch(order);
    }

    private boolean paidStatusMatch(Order order) {
        return isPaid == order.getPaidStatus();
    }

    private boolean deliveredStatusMatch(Order order) {
        return isDelivered == order.getDeliveryStatus();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderDeliveredOrPaidPredicate) // instanceof handles nulls
                && (isDelivered == ((OrderDeliveredOrPaidPredicate) other).isDelivered
                && isPaid == ((OrderDeliveredOrPaidPredicate) other).isPaid);
    }
}
