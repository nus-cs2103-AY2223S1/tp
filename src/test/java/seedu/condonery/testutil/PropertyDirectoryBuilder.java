package seedu.condonery.testutil;

import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code PropertyDirectory ab = new PropertyDirectoryBuilder().withPerson("John", "Doe").build();}
 */
public class PropertyDirectoryBuilder {

    private final PropertyDirectory propertyDirectory;

    public PropertyDirectoryBuilder() {
        propertyDirectory = new PropertyDirectory();
    }

    public PropertyDirectoryBuilder(PropertyDirectory propertyDirectory) {
        this.propertyDirectory = propertyDirectory;
    }

    /**
     * Adds a new {@code Person} to the {@code PropertyDirectory} that we are building.
     */
    public PropertyDirectoryBuilder withProperty(Property property) {
        propertyDirectory.addProperty(property);
        return this;
    }

    public PropertyDirectory build() {
        return propertyDirectory;
    }
}
