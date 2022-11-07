package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Lesson;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static User getSampleUser() {
        return new ExistingUser(new Name("Koh Rui Jie"), new Phone("80000000"), new Email("kohrj@example.com"),
                new Address("somewhere near bishan"), new Github("rjkoh"),
                getCurrentModuleSet("CS2103T"),
                getPreviousModuleSet("CS1101S", "CS1231S"), getPlannedModuleSet("CS2109S"));
    }

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Github("yeoh-alex"),
                getTagSet("friends"), getCurrentModuleSet("CS2103T"),
                getPreviousModuleSet("CS1101S"), getPlannedModuleSet("CS2109S")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Github("bern1ceyu"),
                getTagSet("colleagues", "friends"), getCurrentModuleSet("CS2103T"),
                    getPreviousModuleSet("CS1101S"), getPlannedModuleSet("CS2109S")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Github("charlotteoliveirio"),
                getTagSet("neighbours"), getCurrentModuleSet("CS2103T"),
                    getPreviousModuleSet("CS1101S"), getPlannedModuleSet("CS2109S")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Github("david-li"),
                getTagSet("family"), getCurrentModuleSet("CS2103T"),
                    getPreviousModuleSet("CS1101S"), getPlannedModuleSet("CS2109S")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Github("irfannnI"),
                getTagSet("classmates"), getCurrentModuleSet("CS2103T"),
                    getPreviousModuleSet("CS1101S"), getPlannedModuleSet("CS2109S")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Github("Roy-the-Boy"),
                getTagSet("colleagues"), getCurrentModuleSet("CS2103T"),
                    getPreviousModuleSet("CS1101S"), getPlannedModuleSet("CS2109S"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        sampleAb.addUser(getSampleUser());
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

    public static Set<CurrentModule> getCurrentModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(CurrentModule::new)
                .collect(Collectors.toSet());
    }

    public static Set<PlannedModule> getPlannedModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(PlannedModule::new)
                .collect(Collectors.toSet());
    }

    public static Set<PreviousModule> getPreviousModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(PreviousModule::new)
                .collect(Collectors.toSet());
    }

    public static Set<Lesson> getLessonSet(Lesson... lessons) {
        return Arrays.stream(lessons).collect(Collectors.toSet());
    }

}
