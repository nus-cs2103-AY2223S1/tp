package seedu.address.logic.sortcomparators;

import seedu.address.model.buyer.Name;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class NameComparator implements Comparator<Name> {

    public final Order order;

    /**
     * Constructs a {@code NameComparator}.
     *
     * @param order The specified order of comparison.
     */
    public NameComparator(Order order) {
        requireNonNull(order);
        this.order = order;
    }
    
    @Override
    public int compare(Name firstName, Name secondName) {
        int comparisonValue = firstName.fullName.compareTo(secondName.fullName);
        return order.equals(new Order("ASC")) ? comparisonValue : -comparisonValue;
    }
    
    @Override
    public String toString() {
        return "Sorted by name in " + order;
    }
}
