package tracko.model.util;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.item.Description;
import tracko.model.item.Item;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Item ITEM_1 = new Item(new ItemName("Chair"),
        new Description("Carved mahogany wooden chair"),
          new Quantity(300), new HashSet<>(), new Price(new BigDecimal(80)), new Price(new BigDecimal(150)));
    private static final Item ITEM_2 = new Item(new ItemName("Pillow"),
        new Description("Ergonomic with recycled polystyrene filling"),
           new Quantity(300), new HashSet<>(), new Price(new BigDecimal(15)), new Price(new BigDecimal(30)));
    private static final Item ITEM_3 = new Item(new ItemName("Bolster"),
        new Description("Premium cotton filling"),
            new Quantity(300), new HashSet<>(), new Price(new BigDecimal(10)), new Price(new BigDecimal(20)));
    private static final Item ITEM_4 = new Item(new ItemName("Mattress"),
        new Description("King-sized with memory foam"),
            new Quantity(300), new HashSet<>(), new Price(new BigDecimal(200)), new Price(new BigDecimal(500)));

    public static Item[] getSampleItems() {
        return new Item[] { ITEM_1, ITEM_2, ITEM_3, ITEM_4 };
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    List.of(new ItemQuantityPair(ITEM_1, new Quantity(2))), true, true),
            new Order(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    List.of(new ItemQuantityPair(ITEM_2, new Quantity(4))), true, false),
            new Order(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    List.of(new ItemQuantityPair(ITEM_3, new Quantity(3))), false, true),
            new Order(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    List.of(new ItemQuantityPair(ITEM_4, new Quantity(1))), false, false),
        };
    }

    public static ReadOnlyTrackO getSampleTrackO() {
        TrackO sampleTrackO = new TrackO();
        for (Item sampleItem: getSampleItems()) {
            sampleTrackO.addItem(sampleItem);
        }
        for (Order sampleOrder : getSampleOrders()) {
            sampleTrackO.addOrder(sampleOrder);
        }
        return sampleTrackO;
    }
}
