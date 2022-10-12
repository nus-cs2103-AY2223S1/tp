package seedu.address.testutil;

import seedu.address.model.ProportyBook;
import seedu.address.model.property.Property;

/**
 * A utility class to help with building ProportyBook objects.
 */
public class PropertyModelBuilder {

    private ProportyBook proportyBook;

    public PropertyModelBuilder() {
        proportyBook = new ProportyBook();
    }

    public PropertyModelBuilder(ProportyBook proportyBook) {
        this.proportyBook = proportyBook;
    }

    /**
     * Adds a new {@code Property} to the {@code ProportyBook} that we are building.
     */
    public PropertyModelBuilder withProperty(Property property) {
        proportyBook.addProperty(property);
        return this;
    }

    public ProportyBook build() {
        return proportyBook;
    }
}
