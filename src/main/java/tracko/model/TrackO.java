package tracko.model;

import java.util.List;

import javafx.collections.ObservableList;
import tracko.model.order.Order;
import tracko.model.order.OrderList;

/**
 * Wraps all data at the root level
 */
public class TrackO implements ReadOnlyTrackO {

    private final OrderList orders;

    {
        orders = new OrderList();
    }

    public TrackO() {}

    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Adds an order to be tracked.
     * @param order The order to be added
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof TrackO)
            && orders.equals(((TrackO) other).orders);
    }

    @Override
    public int hashCode() {
        return orders.hashCode();
    }
}
