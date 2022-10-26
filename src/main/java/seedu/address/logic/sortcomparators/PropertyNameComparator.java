package seedu.address.logic.sortcomparators;

import seedu.address.model.property.PropertyName;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

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
        int comparisonValue = firstName.fullName.compareTo(secondName.fullName);
        return order.equals(new Order("ASC")) ? comparisonValue : -comparisonValue;
    }

    @Override
    public String toString() {
        return "Sorted by name in " + order;
    }
}
