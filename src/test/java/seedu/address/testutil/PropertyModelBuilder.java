package seedu.address.testutil;

import seedu.address.model.PropertyModel;
import seedu.address.model.property.Property;

/**
 * A utility class to help with building PropertyModel objects.
 */
public class PropertyModelBuilder {

    private PropertyModel propertyModel;

    public PropertyModelBuilder() {
        propertyModel = new PropertyModel();
    }

    public PropertyModelBuilder(PropertyModel propertyModel) {
        this.propertyModel = propertyModel;
    }

    /**
     * Adds a new {@code Property} to the {@code PropertyModel} that we are building.
     */
    public PropertyModelBuilder withProperty(Property property) {
        propertyModel.addProperty(property);
        return this;
    }

    public PropertyModel build() {
        return propertyModel;
    }
}
