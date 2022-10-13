package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Address;
import seedu.address.model.person.Item;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("ABC Pte Ltd"), new Phone("67089005"), new Price("$1.00"), new Item("Ginger"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("Supplier")),
                new Person(new Name("Ya Shu Egg"), new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("Supplier")),
                new Person(new Name("KyEggs"), new Phone("61240985"), new Price("$1.00"), new Item("Egg"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("Supplier")),
                new Person(new Name("Goh Supplies"), new Phone("69008045"), new Price("$1.50"), new Item("Garlic"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("Supplier")),
                new Person(new Name("Soho Singapore"), new Phone("64300567"), new Price("$1.85"), new Item("Chicken"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("Supplier")),
                new Person(new Name("Balas Market"), new Phone("62624417"), new Price("$1.80"), new Item("Chicken"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("Supplier"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
                new Task("Stock up on Gas", "2022-10-05",
                        true, getTagSet("Fuel")),
                new Task("Stock up on Peanuts", "2022-10-05",
                        false, getTagSet("Food")),
                new Task("Stock up on Cooking Oil", "2022-10-05",
                        false, getTagSet("Fuel")),
        };
    }

    public static SupplyItem[] getSampleSupplyItems() {
        return new SupplyItem[]{
                new SupplyItem("Ginger", 5, 2, new Person(new Name("Ya Shu Egg"),
                        new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("Supplier")), getTagSet("Item")),
                new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                        new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("Supplier")), getTagSet("Item"))
        };
    }

    public static SupplyItem[] getSampleSupplyItems() {
        return new SupplyItem[]{
            new SupplyItem("Ginger", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("Supplier")), getTagSet("Item")),
            new SupplyItem("Egg", 5, 2, new Person(new Name("Ya Shu Egg"),
                new Phone("63450864"), new Price("$1.10"), new Item("Egg"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("Supplier")), getTagSet("Item"))
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

    public static ReadOnlyInventory getSampleInventory() {
        Inventory sampleInv = new Inventory();
        for (SupplyItem sampleSupplyItem: getSampleSupplyItems()) {
            sampleInv.addSupplyItem(sampleSupplyItem);
        }

        return sampleInv;
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