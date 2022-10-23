package tracko.model.order;

import java.util.function.Predicate;

public class OrderDeliveredOrPaidPredicate implements Predicate<Order> {
    Boolean isPaid;
    Boolean isDelivered;
    Boolean filterPaid;
    Boolean filterDelivered;

    public OrderDeliveredOrPaidPredicate(Boolean filterPaid, Boolean filterDelivered, Boolean isPaid, Boolean isDelivered) {
        this.filterPaid = filterPaid;
        this.filterDelivered = filterDelivered;
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
    }

    @Override
    public boolean test(Order order) {
        if (filterPaid && filterDelivered) { // case 1: both -p/P and -d/D tags are present
            return paidStatusMatch(order) && deliveredStatusMatch(order);
        }

        if (filterDelivered) { // case 2: only -d/D tags present, only filter by delivery status
            return deliveredStatusMatch(order);
        }

        if (filterPaid) { // case 3: only -p/P tags present, only filter by payment status
            return paidStatusMatch(order);
        }

        return true; // case 4: no tags present, do not filter by delivery or payment status
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
