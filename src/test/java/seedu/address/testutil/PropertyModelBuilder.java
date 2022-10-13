package seedu.address.testutil;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;

/**
 * A utility class to help with building PropertyBook objects.
 */
public class PropertyModelBuilder {

    private PropertyBook propertyBook;

    public PropertyModelBuilder() {
        propertyBook = new PropertyBook();
    }

    public PropertyModelBuilder(PropertyBook propertyBook) {
        this.propertyBook = propertyBook;
    }

    /**
     * Adds a new {@code Property} to the {@code PropertyBook} that we are building.
     */
    public PropertyModelBuilder withProperty(Property property) {
        propertyBook.addProperty(property);
        return this;
    }

    public PropertyBook build() {
        return propertyBook;
    }
}
