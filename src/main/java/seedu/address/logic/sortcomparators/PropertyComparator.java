package seedu.address.logic.sortcomparators;

import seedu.address.model.pricerange.property.Price;
import seedu.address.model.pricerange.property.Property;
import seedu.address.model.pricerange.property.PropertyName;

import java.util.Comparator;
import java.util.Optional;

/**
 * A comparator to compare two Properties in the list.
 */
public class PropertyComparator implements Comparator<Property> {
    private Optional<Comparator<PropertyName>> propertyNameComparator;
    private Optional<Comparator<Price>> priceComparator;

    /**
     * Creates a BuyerComparator object.
     */
    public PropertyComparator(Comparator<PropertyName> propertyNameComparator, Comparator<Price> priceComparator) {
        this.propertyNameComparator = Optional.ofNullable(propertyNameComparator);
        this.priceComparator = Optional.ofNullable(priceComparator);
    }

    @Override
    public int compare(Property firstProperty, Property secondProperty) {
        if (propertyNameComparator.isPresent()) {
            return propertyNameComparator.get().compare(firstProperty.getPropertyName(),
                    secondProperty.getPropertyName());
        } else {
            return priceComparator.get()
                    .compare(firstProperty.getPrice(), secondProperty.getPrice());
        }


    }

    @Override
    public String toString() {
        if (propertyNameComparator.isPresent()) {
            return propertyNameComparator.get().toString();
        } else {
            return priceComparator.get().toString();
        }
    }
}
