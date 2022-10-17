package tracko.model.util;

import java.util.HashSet;
import java.util.List;

import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.item.Description;
import tracko.model.item.Item;
import tracko.model.item.ItemName;
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

    public static Item[] getSampleItems() {
        return new Item[] {
                new Item(new ItemName("Chair"), new Description("Carved mahogany wooden chair"),
                        new Quantity(300), new HashSet<>()),
                new Item(new ItemName("Pillow"), new Description("Ergonomic with recycled polystyrene filling"),
                        new Quantity(300), new HashSet<>()),
                new Item(new ItemName("Bolster"), new Description("Premium cotton filling"),
                        new Quantity(300), new HashSet<>()),
                new Item(new ItemName("Mattress"), new Description("King-sized with memory foam"),
                        new Quantity(300), new HashSet<>()),

        };
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    List.of(new ItemQuantityPair(getSampleItems()[0], new Quantity(2)))),
            new Order(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    List.of(new ItemQuantityPair(getSampleItems()[1], new Quantity(4)))),
            new Order(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    List.of(new ItemQuantityPair(getSampleItems()[2], new Quantity(3)))),
            new Order(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    List.of(new ItemQuantityPair(getSampleItems()[3], new Quantity(1)))),
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
