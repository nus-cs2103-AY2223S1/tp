package tracko.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;
import tracko.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final InventoryItem INVENTORY_ITEM_1 = new InventoryItem(new ItemName("Chair"),
        new Description("Carved mahogany wooden chair"),
          new Quantity(300), getTagSet("Furniture"),
            new Price(149.99), new Price(80.50));
    private static final InventoryItem INVENTORY_ITEM_2 = new InventoryItem(new ItemName("Pillow"),
        new Description("Ergonomic with recycled polystyrene filling"),
           new Quantity(300), getTagSet("Bedroom"),
                new Price(29.99), new Price(14.99));
    private static final InventoryItem INVENTORY_ITEM_3 = new InventoryItem(new ItemName("Bolster"),
        new Description("Premium cotton filling"),
            new Quantity(300), getTagSet("Bedroom", "Premium"),
                new Price(20.00), new Price(10.00));
    private static final InventoryItem INVENTORY_ITEM_4 = new InventoryItem(new ItemName("Mattress"),
        new Description("King-sized with memory foam"),
            new Quantity(300), getTagSet("Bedroom", "Limited"),
                new Price(500.00), new Price(200.00));

    public static InventoryItem[] getSampleItems() {
        return new InventoryItem[] {INVENTORY_ITEM_1, INVENTORY_ITEM_2, INVENTORY_ITEM_3, INVENTORY_ITEM_4};
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    new ArrayList<ItemQuantityPair>(
                            List.of(new ItemQuantityPair(INVENTORY_ITEM_1.getRecordedItem(), new Quantity(2)))
                    ), true, true),
            new Order(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new ArrayList<ItemQuantityPair>(
                            List.of(new ItemQuantityPair(INVENTORY_ITEM_2, new Quantity(4)))
                    ), true, false),
            new Order(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new ArrayList<ItemQuantityPair>(
                            List.of(new ItemQuantityPair(INVENTORY_ITEM_3, new Quantity(3)))
                    ), false, true),
            new Order(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new ArrayList<ItemQuantityPair>(
                            List.of(new ItemQuantityPair(INVENTORY_ITEM_4, new Quantity(1)))
                    ), false, false),
        };
    }

    public static ReadOnlyTrackO getSampleTrackO() {
        TrackO sampleTrackO = new TrackO();
        for (InventoryItem sampleInventoryItem : getSampleItems()) {
            sampleTrackO.addItem(sampleInventoryItem);
        }
        for (Order sampleOrder : getSampleOrders()) {
            sampleTrackO.addOrder(sampleOrder);
        }
        return sampleTrackO;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
