package tracko.model;

import static java.util.Objects.requireNonNull;

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

    /**
     * Creates an AddressBook using the data in the {@code toBeCopied}
     */
    public TrackO(ReadOnlyTrackO toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackO newData) {
        requireNonNull(newData);

        setOrders(newData.getOrderList());
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
