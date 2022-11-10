package seedu.codeconnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.codeconnect.model.AddressBook;
import seedu.codeconnect.model.ReadOnlyAddressBook;
import seedu.codeconnect.model.ReadOnlyTaskList;
import seedu.codeconnect.model.TaskList;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.person.Address;
import seedu.codeconnect.model.person.Email;
import seedu.codeconnect.model.person.Github;
import seedu.codeconnect.model.person.Name;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.person.Phone;
import seedu.codeconnect.model.person.Telegram;
import seedu.codeconnect.model.tag.Tag;
import seedu.codeconnect.model.task.Deadline;
import seedu.codeconnect.model.task.Status;
import seedu.codeconnect.model.task.Task;
import seedu.codeconnect.model.task.TaskName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                    getModuleSet("CS1231", "CS101S", "ES2660", "IS1103", "ES2660"),
                    new Github("AlexY"), new Telegram("alexyeoh")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"),
                    getModuleSet("CS2030S", "CS2040S", "GEA1000", "ES2660", "MA2001"),
                    new Github("bernyu"), new Telegram("bernicee")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"),
                    getModuleSet("CS2100", "CS2101", "GEN2001", "ST2334", "CS2103T"),
                    new Github("charlotteoli"), new Telegram("charoli")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"),
                    getModuleSet("CS1101S", "GEA1000", "IS1103", "MA1521", "MA2001"),
                    new Github("davidli"), new Telegram("davidlii")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                    getModuleSet("CS2001", "CS2019S", "CS2106", "CS2107", "GEA1000"),
                    new Github("IrfanIbrahim"), new Telegram("irfann")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                    getModuleSet("CS2103T", "CS2019S", "CS2106", "CS2107", "GEA1000"),
                    new Github("Roy"), new Telegram("Roybala"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("Lab2"), new Module("CS2030S"), new Deadline("2022-02-02 23:59"),
                    new Status(false)),
            new Task(new TaskName("Problem Set 2"), new Module("CS2040S"),
                    new Deadline("2022-02-04 23:59"), new Status(false))
        };

    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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


    /**
     * Returns a module set containing the list of strings given.
     */
    public static Set<Module> getModuleSet(String... moduleNames) {
        return Arrays.stream(moduleNames)
                .map(Module::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;

    }

}
