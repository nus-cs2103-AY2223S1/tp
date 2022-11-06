package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {

    public static final Tag CS2103T = new TagBuilder()
            .withName("CS2103T")
            .withCount(2)
            .build();
    public static final Tag CS2101 = new TagBuilder()
            .withName("CS2101")
            .withCount(1)
            .build();
    public static final Tag FRIENDS = new TagBuilder()
            .withName("friends")
            .withCount(1)
            .build();
    public static final Tag OWESMONEY = new TagBuilder()
            .withName("owesMoney")
            .withCount(1)
            .build();

    private TypicalTags() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons, tasks and tags.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }
        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101, FRIENDS, OWESMONEY));
    }
}
