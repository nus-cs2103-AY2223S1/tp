package friday.logic.parser;

import static friday.commons.util.AppUtil.checkArgument;

/**
 * The order for sorting students. Can be ascending or descending.
 */
public class Order {
    public static final String MESSAGE_CONSTRAINTS = "Order should only be 'asc' or 'desc', and it should not be blank";

    private final String order;

    /**
     * Constructs an {@code Order}.
     *
     * @param order A valid order.
     */
    public Order(String order) {
        checkArgument(isValidOrder(order), MESSAGE_CONSTRAINTS);
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public String toString() {
        return getOrder();
    }

    public static boolean isValidOrder(String order) {
        return order.equals("asc") || order.equals("desc");
    }

    @Override
    public int hashCode() {
        return order == null ? 0 : order.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Order otherOrder = (Order) obj;
        return otherOrder.getOrder().equals(getOrder());
    }
}
