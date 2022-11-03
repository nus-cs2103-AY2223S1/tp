package seedu.address.testutil;

import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_ADDRESS_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_ADDRESS_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_CHARACTERISTICS_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_CHARACTERISTICS_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_DESCRIPTION_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_DESCRIPTION_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_NAME_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_NAME_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_NAME_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_NAME_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_PHONE_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_PHONE_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_PRICE_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_PRICE_PROPERTY1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;

/**
 * A utility class containing a list of {@code Property} objects to be used in tests.
 */
public class TypicalProperties {

    public static final Property PEAKRESIDENCE = new PropertyBuilder().withName("Peak Residence")
            .withAddress("333 Thompson Road").withPrice("3000000")
            .withDescription("private condo")
            .build();

    public static final Property HUT = new PropertyBuilder().withName("HUT")
            .withAddress("333 College Avenue").withPrice("1000")
            .withDescription("hdb")
            .build();

    // Manually added - Property's details found in {@code CommandTestUtil}
    public static final Property PROPERTY1 = new PropertyBuilder().withName(VALID_NAME_PROPERTY1)
            .withAddress(VALID_ADDRESS_PROPERTY1).withPrice(VALID_PRICE_PROPERTY1)
            .withDescription(VALID_DESCRIPTION_PROPERTY1).withCharacteristics(VALID_CHARACTERISTICS_PROPERTY1)
            .withOwner(VALID_OWNER_NAME_PROPERTY1, VALID_OWNER_PHONE_PROPERTY1).build();

    public static final Property HOME = new PropertyBuilder().withName(VALID_NAME_HOME).withAddress(VALID_ADDRESS_HOME)
            .withPrice(VALID_PRICE_HOME).withDescription(VALID_DESCRIPTION_HOME)
            .withCharacteristics(VALID_CHARACTERISTICS_HOME)
            .withOwner(VALID_OWNER_NAME_HOME, VALID_OWNER_PHONE_HOME)
            .build();

    private TypicalProperties() {} // prevents instantiation

    /**
     * Returns a {@code PropertyBook} with all the typical properties.
     */
    public static PropertyBook getTypicalPropertyBook() {
        PropertyBook propertyBook = new PropertyBook();
        for (Property property : getTypicalProperties()) {
            propertyBook.addProperty(property);
        }
        return propertyBook;
    }

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(PEAKRESIDENCE, HUT, HOME));
    }
}

