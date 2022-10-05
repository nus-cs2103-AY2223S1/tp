package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer.CustomerBuilder(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), getTagSet("friends"))
                    .setAddress(new Address("Blk 30 Geylang Street 29, #06-40")).build(),
            new Customer.CustomerBuilder(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), getTagSet("colleagues", "friends"))
                    .setAddress(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")).build(),
            new Customer.CustomerBuilder(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), getTagSet("neighbours"))
                    .setAddress(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")).build(),
            new Customer.CustomerBuilder(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), getTagSet("family"))
                    .setAddress(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")).build(),
            new Customer.CustomerBuilder(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), getTagSet("classmates"))
                    .setAddress(new Address("Blk 47 Tampines Street 20, #17-35")).build(),
            new Customer.CustomerBuilder(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), getTagSet("colleagues"))
                    .setAddress(new Address("Blk 45 Aljunied Street 85, #11-31")).build()
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        return sampleAb;
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
