package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new Phone("87438807")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new Phone("99272758")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), new Phone("93210283")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), new Phone("91031282")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new Phone("92492021")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new Phone("92624417")),
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleCode("CS2103T"), new ModuleTitle("Software Engineering"),
                    Arrays.asList(new Task("Study Stackoverflow"),
                            new Task("Create OOP design patterns")),
                    new HashSet<>(Arrays.asList(
                            new Link("2103 Home Page",
                                    "https://nus-cs2103-ay2223s1.github.io/website/"),
                            new Link("Team Repo", "https://github.com/AY2223S1-CS2103T-T10-1/tp"))),
                    new HashSet<>(Arrays.asList(
                            new Person(new Name("Charlotte Oliveiro"),
                                    new Email("charlotte@example.com"), new Phone("93210283")),
                            new Person(new Name("David Li"),
                                    new Email("lidavid@example.com"), new Phone("91031282"))
                    ))),
            new Module(new ModuleCode("CS2106"), new ModuleTitle("Introduction to Operating Systems"),
                    Arrays.asList(new Task("How to use linux"),
                            new Task("Practise MIPS")),
                    new HashSet<>(Arrays.asList(
                            new Link("Slide into DMs", "https://youtu.be/ddgKA7-v_MM"),
                            new Link("Choosing Shoes", "https://youtu.be/BQ4c54rCJ_k"),
                            new Link("Lost My Mind", "https://youtu.be/ic6va8pKQ7k"),
                            new Link("Ash", "https://youtu.be/nRac7q9Y6o4"),
                            new Link("Dr Strange", "https://youtu.be/lXOZFrnOBVA"),
                            new Link("Interstellar", "https://youtu.be/U7rQt6pWTkg"),
                            new Link("MapleStory", "https://youtu.be/i3pfsCS7fWI"),
                            new Link("Giovanni", "https://youtu.be/i2U50K13-Hg"))),
                    new HashSet<>(Arrays.asList(
                            new Person(new Name("Roy Balakrishnan"),
                                    new Email("royb@example.com"), new Phone("92624417")),
                            new Person(new Name("Irfan Ibrahim"),
                                    new Email("irfan@example.com"), new Phone("92492021"))
                    ))),
            new Module(new ModuleCode("GE3238"), new ModuleTitle("GIS Design and Practices"),
                    Arrays.asList(new Task("Make NUS 3D Mapping by Friday"),
                            new Task("View cool visulisations on NUS GIS for ideas")),
                    new HashSet<>(Arrays.asList(
                            new Link("QGIS Home Page", "https://www.qgis.org/en/site/"),
                            new Link("NUS GIS", "https://nusgis.org/"))),
                    new HashSet<>(Arrays.asList(
                            new Person(new Name("Alex Yeoh"),
                                    new Email("alexyeoh@example.com"), new Phone("87438807")),
                            new Person(new Name("Bernice Yu"),
                                    new Email("berniceyu@example.com"), new Phone("99272758"))
                    ))),
            new Module(new ModuleCode("MA2001"), new ModuleTitle("Linear Algebra"),
                    Arrays.asList(new Task("Figure out matrix addition"),
                            new Task("Review vectors in general")),
                    new HashSet<>(Arrays.asList(
                            new Link("Vectors 1 Utube",
                                    "https://www.youtube.com/watch?v=fNk_zzaMoSs"),
                            new Link("MIT Textbook", "https://math.mit.edu/~gs/linearalgebra/"))),
                    new HashSet<>(Arrays.asList(
                            new Person(new Name("David Li"),
                                    new Email("lidavid@example.com"), new Phone("91031282")),
                            new Person(new Name("Irfan Ibrahim"),
                                    new Email("irfan@example.com"), new Phone("92492021"))
                    ))),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Module sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        return sampleAb;
    }
}
