package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.PropertyModel;
import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 * NOTE: Already implemented in another PR, to be merged.
 */
public class TypicalProperties {

    private TypicalProperties() {} // prevents instantiation

    public static PropertyModel getTypicalPropertyModel() {
        return new PropertyModel();
    }

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>();
    }
}
