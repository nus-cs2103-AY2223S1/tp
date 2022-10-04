package tracko.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.order.Order;
import tracko.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
                new Order("Alex Yeoh", "87438807", "alexyeoh@example.com",
                        "Blk 30 Geylang Street 29, #06-40",
                        "keychain", 3),
                new Order("Bernice Yu", "99272758", "berniceyu@example.com",
                        "Blk 30 Lorong 3 Serangoon Gardens, #07-18",
                        "bolster", 1),
                new Order("Charlotte Oliveiro", "93210283", "charlotte@example.com",
                        "Blk 11 Ang Mo Kio Street 74, #11-04",
                        "pillow", 1),
                new Order("David Li", "91031282", "lidavid@example.com",
                        "Blk 436 Serangoon Gardens Street 26, #16-43",
                        "mattress", 2)
        };
    }

    public static ReadOnlyTrackO getSampleTrackO() {
        TrackO sampleTrackO = new TrackO();
        for (Order sampleOrder : getSampleOrders()) {
            sampleTrackO.addOrder(sampleOrder);
        }
        return sampleTrackO;
    }
}
