package tracko.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tracko.model.TrackO;
import tracko.model.item.InventoryItem;
import tracko.model.item.InventoryList;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final InventoryItem DEFAULT_INVENTORY_ITEM = new InventoryItemBuilder().build();

    // Following items to be instantiated as a test InventoryList
    public static final InventoryItem INVENTORY_ITEM_1 = new InventoryItemBuilder().withItemName("Sofa")
            .withQuantity(200).withDescription("Made of leather")
            .withSellPrice(150.00)
            .withCostPrice(50.00).build();
    public static final InventoryItem INVENTORY_ITEM_2 = new InventoryItemBuilder().withItemName("Bed")
            .withQuantity(50).withDescription("Comes with linen")
            .withSellPrice(200.00)
            .withCostPrice(50.00).build();
    public static final InventoryItem INVENTORY_ITEM_3 = new InventoryItemBuilder().withItemName("School Bag")
            .withQuantity(3).withDescription("Suitable for secondary kids")
            .withSellPrice(34.00)
            .withCostPrice(5.00).build();
    public static final InventoryItem INVENTORY_ITEM_4 = new InventoryItemBuilder().withItemName("Dining Table")
            .withQuantity(25).withDescription("Comes with table cloth")
            .withSellPrice(199.99)
            .withCostPrice(49.99).build();
    public static final InventoryItem INVENTORY_ITEM_5 = new InventoryItemBuilder().withItemName("Pants")
            .withQuantity(52).withDescription("Made with denim")
            .withSellPrice(40.0)
            .withCostPrice(50.0).build();
    public static final InventoryItem INVENTORY_ITEM_6 = new InventoryItemBuilder().withItemName("Bed")
            .withQuantity(50).withDescription("Comes with linen")
            .withSellPrice(199.9)
            .withCostPrice(49.9).build();
    public static final InventoryItem INVENTORY_ITEM_7 = new InventoryItemBuilder().withItemName("Slippers")
            .withQuantity(48).withDescription("Cute shark slippers")
            .withSellPrice(10.00)
            .withCostPrice(2.00).build();

    // Manually added
    public static final InventoryItem INVENTORY_ITEM_8 = new InventoryItemBuilder().withItemName("Keychain")
            .withQuantity(95).withDescription("Small copper keychain")
            .withSellPrice(0.50)
            .withCostPrice(4.99).build();
    public static final InventoryItem INVENTORY_ITEM_9 = new InventoryItemBuilder().withItemName("Mechanical Pencil")
            .withQuantity(278).withDescription("Mechanical pencil with rubber grip")
            .withSellPrice(0.30)
            .withCostPrice(2.50).build();

    // Manually added
    public static final InventoryItem INVENTORY_ITEM_10 = new InventoryItemBuilder().withItemName("Eraser")
            .withQuantity(209).withDescription("Plastic eraser")
            .withSellPrice(0.05)
            .withCostPrice(0.70).build();
    public static final InventoryItem INVENTORY_ITEM_11 = new InventoryItemBuilder().withItemName("Stapler")
            .withQuantity(76).withDescription("Portable handheld stapler")
            .withSellPrice(10.00)
            .withCostPrice(20.00).build();

    public static final InventoryList INVENTORY_LIST = getTypicalInventoryList();

    /**
     * Returns an {@code TrackO} with all the typical {@code Item}.
     */
    public static TrackO getTrackOWithTypicalItems() {
        TrackO trackO = new TrackO();
        for (InventoryItem inventoryItem : getTypicalItems()) {
            trackO.addItem(inventoryItem);
        }
        return trackO;
    }

    public static List<InventoryItem> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(DEFAULT_INVENTORY_ITEM, INVENTORY_ITEM_1, INVENTORY_ITEM_2,
            INVENTORY_ITEM_3, INVENTORY_ITEM_4, INVENTORY_ITEM_5, INVENTORY_ITEM_6, INVENTORY_ITEM_7));
    }

    /**
     * Returns an {@code InventoryList} with all the typical {@code Item}s;
     */
    private static InventoryList getTypicalInventoryList() {
        InventoryList inventoryList = new InventoryList();
        inventoryList.setItems(getTypicalItems());
        return inventoryList;
    }
}
