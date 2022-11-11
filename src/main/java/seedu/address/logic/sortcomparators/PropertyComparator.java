package seedu.address.logic.sortcomparators;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.price.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;

/**
 * A comparator to compare two Properties in the list.
 */
public class PropertyComparator implements Comparator<Property> {
    private Optional<Comparator<PropertyName>> propertyNameComparator;
    private Optional<Comparator<Price>> priceComparator;

    private Optional<Comparator<LocalDateTime>> timeComparator;

    /**
     * Creates a BuyerComparator object.
     */
    public PropertyComparator(Comparator<PropertyName> propertyNameComparator,
                              Comparator<Price> priceComparator,
                              Comparator<LocalDateTime> timeComparator) {
        this.propertyNameComparator = Optional.ofNullable(propertyNameComparator);
        this.priceComparator = Optional.ofNullable(priceComparator);
        this.timeComparator = Optional.ofNullable(timeComparator);
    }

    @Override
    public int compare(Property firstProperty, Property secondProperty) {
        if (propertyNameComparator.isPresent()) {
            return propertyNameComparator.get().compare(firstProperty.getPropertyName(),
                    secondProperty.getPropertyName());
        } else if (priceComparator.isPresent()) {
            return priceComparator.get()
                    .compare(firstProperty.getPrice(), secondProperty.getPrice());
        } else {
            return timeComparator.get()
                    .compare(firstProperty.getPropertyEntryTime(),
                            secondProperty.getPropertyEntryTime());
        }


    }

    @Override
    public String toString() {
        if (propertyNameComparator.isPresent()) {
            return propertyNameComparator.get().toString();
        } else if (priceComparator.isPresent()) {
            return priceComparator.get().toString();
        } else {
            return timeComparator.get().toString();
        }
    }
}
