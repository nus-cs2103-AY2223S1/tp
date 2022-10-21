package tracko.model.order;

import java.util.Comparator;

/**
 * Implements the comparison of a {@code Order}'s {@code timeCreated} based on the keyword given.
 */
public class OrderDateTimeComparator implements Comparator<Order> {
    private final String keyword;

    public OrderDateTimeComparator(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int compare(Order order1, Order order2) {
        if (keyword.equalsIgnoreCase("old")) {
            return order1.getTimeCreated().compareTo(order2.getTimeCreated());
        }

        if (keyword.equalsIgnoreCase("new")) {
            return order2.getTimeCreated().compareTo(order1.getTimeCreated());
        }

        //This line should not be reached
        return -2;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderDateTimeComparator // instanceof handles nulls
                && keyword.equals(((OrderDateTimeComparator) other).keyword)); // state check
    }

}
