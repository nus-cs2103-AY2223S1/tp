package seedu.address.logic.sortcomparators;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.property.PropertyName;


/**
 * A comparator to compare two PropertyNames.
 */
public class PropertyNameComparator implements Comparator<PropertyName> {

    private final Order order;

    /**
     * Constructs a {@code PropertyNameComparator}.
     *
     * @param order The specified order of comparison.
     */
    public PropertyNameComparator(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public int compare(PropertyName firstName, PropertyName secondName) {
        int comparisonValue = firstName.compareTo(secondName);
        return order.equals(new Order("ASC")) ? comparisonValue : -comparisonValue;
    }

    @Override
    public String toString() {
        return "Sorted by property name in " + order;
    }
}
