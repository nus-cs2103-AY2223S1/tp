package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_HOME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HOME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_HOME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ProportyBook;
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
    public static final Property HOME = new PropertyBuilder().withName(VALID_NAME_HOME).withAddress(VALID_ADDRESS_HOME)
            .withPrice(VALID_PRICE_HOME).withDescription(VALID_DESCRIPTION_HOME).build();

    private TypicalProperties() {} // prevents instantiation

    /**
     * Returns a {@code PropertyBook} with all the typical properties.
     */
    public static ProportyBook getTypicalPropertyModel() {
        ProportyBook proportyBook = new ProportyBook();
        for (Property property : getTypicalProperties()) {
            proportyBook.addProperty(property);
        }
        return proportyBook;
    }

    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(PEAKRESIDENCE, HUT, HOME));
    }
}

