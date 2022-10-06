package seedu.address.model.util;

import seedu.address.model.*;
import seedu.address.model.person.*;
import seedu.address.model.supplier.Contact;
import seedu.address.model.supplier.Item;
import seedu.address.model.supplier.Price;
import seedu.address.model.supplier.Supplier;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task("Stock up on Gas", "2022-10-05", getTagSet("Fuel")),
            new Task("Stock up on Peanuts", "2022-10-05", getTagSet("Food")),
            new Task("Stock up on Cooking Oil", "2022-10-05", getTagSet("Fuel")),
        };
    }

    public static Supplier[] getSuppliers() {
        return new Supplier[] {
                new Supplier(new seedu.address.model.supplier.Name("ABC supplies"),new Contact("65430985"),new Price("$7.10"),new Item("Ginger"),
                        new seedu.address.model.supplier.Address("153 Thomson Ridge"),getTagSet("Supplier")),
                new Supplier(new seedu.address.model.supplier.Name("Lim supplies"),new Contact("62347266"),new Price("$7.40"),new Item("Onion"),
                        new seedu.address.model.supplier.Address("25 Holland Rise"),getTagSet("Supplier"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }

        return sampleTl;
    }

    public static ReadOnlySupplierList getSampleSupplierList() {
        SupplierList sampleSL = new SupplierList();
        for (Supplier sampleSupplier : getSampleTasks()) {
            sampleSl.addSupplier(sampleSupplier);
        }

        return sampleSL;
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
