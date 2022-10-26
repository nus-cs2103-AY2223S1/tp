package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.MyInsuRec;
import seedu.address.model.ReadOnlyMyInsuRec;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.product.Product;

/**
 * Contains utility methods for populating {@code MyInsuRec} with sample data.
 */
public class SampleDataUtil {
    private static Product[] getSampleProducts() {
        return new Product[] {
            new Product("Product1"),
            new Product("Product2"),
            new Product("Product3"),
            new Product("Product4"),
            new Product("Product5"),
            new Product("Product6")
        };
    }

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), Optional.of(new Email("alexyeoh@example.com")),
                    Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
                    Optional.of(new Birthday(LocalDate.of(1980, 12, 12))),
                    getProductSet("Product1")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), Optional.of(new Email("berniceyu@example.com")),
                    Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
                    Optional.of(new Birthday(LocalDate.of(1989, 3, 24))),
                    getProductSet("Product2")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    Optional.of(new Email("charlotte@example.com")),
                    Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
                    Optional.of(new Birthday(LocalDate.of(1997, 8, 9))),
                    getProductSet("Product3")),
            new Client(new Name("David Li"), new Phone("91031282"), Optional.of(new Email("lidavid@example.com")),
                    Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
                    Optional.of(new Birthday(LocalDate.of(2000, 6, 16))),
                    getProductSet("Product4")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), Optional.of(new Email("irfan@example.com")),
                    Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")),
                    Optional.of(new Birthday(LocalDate.of(1988, 5, 18))),
                    getProductSet("Product5")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), Optional.of(new Email("royb@example.com")),
                    Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")),
                    Optional.of(new Birthday(LocalDate.of(1999, 7, 3))),
                    getProductSet("Product6"))
        };
    }

    public static ReadOnlyMyInsuRec getSampleMyInsuRec() {
        MyInsuRec sampleAb = new MyInsuRec();
        for (Product sampleProduct : getSampleProducts()) {
            sampleAb.addProduct(sampleProduct);
        }
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a product set containing the list of strings given.
     */
    public static Set<Product> getProductSet(String... strings) {
        return Arrays.stream(strings)
                .map(Product::new)
                .collect(Collectors.toSet());
    }

}
