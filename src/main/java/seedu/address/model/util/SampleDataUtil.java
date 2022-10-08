package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.person.*;
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
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),new Price("$1.00"),new Item("Ginger"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("supplier")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Price("$1.00"),new Item("Butter"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("supplier")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Price("$1.00"),new Item("Ginger"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("supplier")),
            new Person(new Name("David Li"), new Phone("91031282"),new Price("$1.00"),new Item("Garlic"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("supplier")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Price("$1.00"),new Item("Egg"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("supplier")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),new Price("$1.00"),new Item("Egg"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("supplier"))
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

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
