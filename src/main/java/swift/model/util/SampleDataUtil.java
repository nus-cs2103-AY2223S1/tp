package swift.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import swift.model.AddressBook;
import swift.model.ReadOnlyAddressBook;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Address;
import swift.model.person.Email;
import swift.model.person.Person;
import swift.model.person.PersonName;
import swift.model.person.Phone;
import swift.model.tag.Tag;
import swift.model.task.Deadline;
import swift.model.task.Description;
import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static ArrayList<Person> getSamplePersons() {
        return new ArrayList<>(Arrays.asList(
                new Person(UUID.fromString("6517916e-80c0-40e1-ac13-7cb870f57d80"),
                        new PersonName("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("clients")),
                new Person(UUID.fromString("049fb6e6-7e43-4075-a1e3-faad028faa0f"),
                        new PersonName("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("developers")),
                new Person(UUID.fromString("5f3f93b9-d839-4d5c-b197-9f3e53ebbb71"),
                        new PersonName("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("developers")),
                new Person(UUID.fromString("f2d431ed-1793-4761-9121-3652441e0ea2"),
                        new PersonName("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("clients")),
                new Person(UUID.fromString("98245b81-7d46-4834-b557-ed2f720110e8"),
                        new PersonName("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("clients")),
                new Person(UUID.fromString("1c6513a5-f530-4995-88ba-c9a7eb77d81c"),
                        new PersonName("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("designers"))
        ));
    }

    public static ArrayList<Task> getSampleTasks() {
        return new ArrayList<>(Arrays.asList(
                new Task(UUID.fromString("08a458c9-4f0e-4819-a716-1876ff57356f"),
                        new TaskName("Discuss user requirements"),
                        Optional.of(new Description("Draw diagrams")),
                        Optional.of(new Deadline("12-12-2022 1400"))),
                new Task(UUID.fromString("9f1d7d61-e75b-41f2-b66b-1ce95ec251e3"),
                        new TaskName("Update developer guide"),
                        Optional.of(new Description("Include UML diagram")),
                        Optional.empty()),
                new Task(UUID.fromString("990b1561-ea5f-498d-96e6-eab5381b609e"),
                        new TaskName("Review PR"),
                        Optional.empty(),
                        Optional.of(new Deadline("14-12-2022 1800"))),
                new Task(UUID.fromString("f2b134b4-d505-463c-bc10-0e72c4566002"),
                        new TaskName("Brainstorm user stories"),
                        Optional.of(new Description("Meeting link: https://www.meeting.com")),
                        Optional.of(new Deadline("14-12-2022 1800")))
        ));
    }

    public static ArrayList<PersonTaskBridge> getSamplePersonTaskBridge() {
        return new ArrayList<>(Arrays.asList(
                new PersonTaskBridge(UUID.fromString("6517916e-80c0-40e1-ac13-7cb870f57d80"),
                        UUID.fromString("08a458c9-4f0e-4819-a716-1876ff57356f")),
                new PersonTaskBridge(UUID.fromString("049fb6e6-7e43-4075-a1e3-faad028faa0f"),
                        UUID.fromString("9f1d7d61-e75b-41f2-b66b-1ce95ec251e3")),
                new PersonTaskBridge(UUID.fromString("049fb6e6-7e43-4075-a1e3-faad028faa0f"),
                        UUID.fromString("990b1561-ea5f-498d-96e6-eab5381b609e")),
                new PersonTaskBridge(UUID.fromString("f2d431ed-1793-4761-9121-3652441e0ea2"),
                        UUID.fromString("f2b134b4-d505-463c-bc10-0e72c4566002"))
        ));
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        sampleAb.setPersons(getSamplePersons());
        sampleAb.setTasks(getSampleTasks());
        sampleAb.setBridges(getSamplePersonTaskBridge());
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
