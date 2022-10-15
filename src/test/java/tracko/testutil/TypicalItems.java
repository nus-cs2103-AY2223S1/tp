package tracko.testutil;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import tracko.model.TrackO;
import tracko.model.items.Item;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item ITEM_1 = new ItemBuilder().withItemName("Sofa")
            .withQuantity(200).withDescription("Made of leather").build();
    public static final Item ITEM_2 = new ItemBuilder().withItemName("Bed")
            .withQuantity(50).withDescription("Comes with linen").build();
    public static final Item ITEM_3 = new ItemBuilder().withItemName("School Bag")
            .withQuantity(3).withDescription("Suitable for secondary kids").build();
    public static final Item ITEM_4 = new ItemBuilder().withItemName("Dining Table")
            .withQuantity(25).withDescription("Comes with table cloth").build();
    public static final Item ITEM_5 = new ItemBuilder().withItemName("Pants")
            .withQuantity(52).withDescription("Made with denim").build();
    public static final Item ITEM_6 = new ItemBuilder().withItemName("Bed")
            .withQuantity(50).withDescription("Comes with linen").build();
    public static final Item ITEM_7 = new ItemBuilder().withItemName("Slippers")
            .withQuantity(48).withDescription("Cute shark slippers").build();

    /**
     * Returns an {@code TrackO} with all the typical {@code Item}.
     */
    public static TrackO getTrackOWithTypicalItems() {
        TrackO trackO = new TrackO();
        for (Item item : getTypicalItems()) {
            trackO.addItem(item);
        }
        return trackO;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(ITEM_1, ITEM_2, ITEM_3, ITEM_4, ITEM_5, ITEM_6, ITEM_7));
    }
}
