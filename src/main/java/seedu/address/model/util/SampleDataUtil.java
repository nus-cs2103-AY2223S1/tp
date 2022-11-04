package seedu.address.model.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        List<Task> taskList = new ArrayList<>();
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"), taskList),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), taskList),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"), taskList),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"), taskList),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"), taskList),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"), taskList)
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
                new Task(new TaskName("Create UIUX Design"),
                        new Description("Use FIGMA"),
                        new Priority(PriorityEnum.getFromString("medium").get()),
                        new TaskCategory(TaskCategoryType.getFromString("uiux").get()),
                        new TaskDeadline(LocalDate.parse("2023-01-02")),
                        getSamplePersons()[0],
                        false),
                new Task(new TaskName("Build PostgreSQL Database"),
                        new Description("Use google cloud database to host the database"),
                        new Priority(PriorityEnum.getFromString("high").get()),
                        new TaskCategory(TaskCategoryType.getFromString("database").get()),
                        new TaskDeadline(LocalDate.parse("2023-01-02")),
                        getSamplePersons()[1],
                        false),
                new Task(new TaskName("Implement Backend API"),
                        new Description("Write API endpoints using NodeJS"),
                        new Priority(PriorityEnum.getFromString("medium").get()),
                        new TaskCategory(TaskCategoryType.getFromString("uiux").get()),
                        new TaskDeadline(LocalDate.parse("2023-01-03")),
                        getSamplePersons()[2],
                        false),
                new Task(new TaskName("Implement Frontend"),
                        new Description("Use ReactJS to create a static web page"),
                        new Priority(PriorityEnum.getFromString("medium").get()),
                        new TaskCategory(TaskCategoryType.getFromString("frontend").get()),
                        new TaskDeadline(LocalDate.parse("2023-01-03")),
                        getSamplePersons()[3],
                        false),
                new Task(new TaskName("Create Presentation"),
                        new Description("Draft a 3-minutes elevator pitch"),
                        new Priority(PriorityEnum.getFromString("low").get()),
                        new TaskCategory(TaskCategoryType.getFromString("presentation").get()),
                        new TaskDeadline(LocalDate.parse("2023-01-04")),
                        getSamplePersons()[4],
                        false),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
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
