package seedu.address.commons.core.order;

/**
 * Represents the order in which the list is
 * sorted either in increasing or decreasing order.
 *
 * {@code Order} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code Order} to avoid having to know what
 * ordering the other component is using to display the list. However, after receiving the {@code Order},
 * that component can convert it back to a boolean if the order will not be passed to a different component again.
 */
public class Order {
    private static final String DECREASING_ALPHABETICAL = "Z-A";
    private static final String INCREASING_ALPHABETICAL = "A-Z";
    public static final String VALID_ORDER = "Please add in either (but not both): "
            + "\n"
            + "-" + DECREASING_ALPHABETICAL
            + "\n"
            + "-" + INCREASING_ALPHABETICAL;
    private boolean isIncreasing;

    /**
     * Order can only be created by calling {@link Order#lexicographicalOrder(String)}.
     */
    private Order(boolean isIncreasing) {
        this.isIncreasing = isIncreasing;
    }

    /**
     * @param order describe in the contact of sorting in lexicographical order.
     * @return Order
     */
    public static Order lexicographicalOrder(String order) {
        if (order.equals(DECREASING_ALPHABETICAL)) {
            return new Order(false);
        }
        if (order.equals(INCREASING_ALPHABETICAL)) {
            return new Order(true);
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns true if order specified is increasing order,
     * otherwise, returns false if order is non-increasing.
     * @return boolean
     */
    public boolean isIncreasingOrder() {
        return this.isIncreasing;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            return this.isIncreasing == order.isIncreasingOrder();
        }
        return this == obj;
    }
}
