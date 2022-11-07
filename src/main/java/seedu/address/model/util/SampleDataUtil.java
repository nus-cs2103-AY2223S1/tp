package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new PersonName("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new PersonName("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new PersonName("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new PersonName("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new PersonName("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new PersonName("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Team[] getSampleTeams() {
        return new Team[] {
            new Team(new TeamName("Frontend")),
            new Team(new TeamName("Backend"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("Create GUI for the app"),
                LocalDate.of(2022, 12, 1), true),
            new Task(new TaskName("Beginning of year meeting"),
                LocalDate.of(2023, 1, 5)),
            new Task(new TaskName("Meeting with Client A"),
                LocalDate.of(2022, 12, 20)),
            new Task(new TaskName("Implement a payment system"),
                null),
            new Task(new TaskName("Implement Feature A using javascript"),
                null)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        Person[] samplePersons = getSamplePersons();
        Team[] sampleTeams = getSampleTeams();
        Task[] sampleTasks = getSampleTasks();

        for (int i = 0; i < sampleTeams.length; i++) {
            sampleAb.addTeam(sampleTeams[i]);
        }

        for (int i = 0; i < samplePersons.length; i++) {
            sampleAb.addPerson(samplePersons[i]);
            sampleAb.addPersonToTeam(samplePersons[i], sampleTeams[i % sampleTeams.length]);
        }

        for (int i = 0; i < sampleTasks.length; i++) {
            sampleAb.addTask(Index.fromZeroBased(i % sampleTeams.length), sampleTasks[i]);
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
     * Returns a deadline with the given date in string.
     */
    public static Optional<LocalDate> getDeadline(String deadline) {
        try {
            return ParserUtil.parseDeadline(deadline);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }
}
