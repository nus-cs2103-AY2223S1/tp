package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskList} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSamplePersons() {
        return new Task[] {
            new Task(new Name("Assignment 1"), new Module("CS2100"), new Deadline("2022-11-02"),
                getTagSet("highPriority")),
            new Task(new Name("Tutorial 4"), new Module("CS2100"), new Deadline("2022-10-07"),
                getTagSet("mediumPriority", "easy")),
            new Task(new Name("Chapter 1 Revision"), new Module("CS2103T"), new Deadline("2022-10-11"),
                getTagSet("revision"))
        };
    }

    public static ReadOnlyTaskList getSampleAddressBook() {
        TaskList sampleAb = new TaskList();
        for (Task sampleTask : getSamplePersons()) {
            sampleAb.addPerson(sampleTask);
        }
        return sampleAb;
    }



    public static Task[] getSampleArchivedTasks() {
        return new Task[] {
            new Task(new Name("Tutorial 1"), new Module("CS2100"), new Deadline("2022-11-01"),
                getTagSet("MediumPriority"), true),
            new Task(new Name("Tutorial 7"), new Module("CS2100"), new Deadline("2022-11-07"),
                getTagSet("lowPriority", "hard"), true),
            new Task(new Name("Chapter 4 Revision"), new Module("CS2103T"), new Deadline("2022-11-14"),
                getTagSet("revision"), true)
        };
    }

    public static ReadOnlyTaskList getSampleArchivedTaskBook() {
        TaskList sampleArchivedTaskBook = new TaskList();
        for (Task sampleTask: getSampleArchivedTasks()) {
            sampleArchivedTaskBook.addPerson(sampleTask);
        }
        return sampleArchivedTaskBook;
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
