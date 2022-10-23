package seedu.address.testutil;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;

/**
 * A utility class to help with building PropertyBook objects.
 */
public class PropertyBookBuilder {

    private PropertyBook propertyBook;

    public PropertyBookBuilder() {
        propertyBook = new PropertyBook();
    }

    public PropertyBookBuilder(PropertyBook propertyBook) {
        this.propertyBook = propertyBook;
    }

    /**
     * Adds a new {@code Property} to the {@code PropertyBook} that we are building.
     */
    public PropertyBookBuilder withProperty(Property property) {
        propertyBook.addProperty(property);
        return this;
    }

    public PropertyBook build() {
        return propertyBook;
    }
}
