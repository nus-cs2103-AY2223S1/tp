package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.task.Contact;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TITLE = "Add task functionality to Arrow";

    private Title title;
    private Deadline deadline;
    private final boolean isCompleted;
    private Set<Contact> contacts;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        isCompleted = false;
        deadline = Deadline.UNSPECIFIED;
        contacts = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        isCompleted = taskToCopy.getCompleted();
        contacts = new HashSet<>(taskToCopy.getAssignedContacts());
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(Deadline deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withContacts(String... contacts) {
        this.contacts = SampleDataUtil.getContactSet(contacts);
        return this;
    }


    public Task build() {
        return new Task(title, false, deadline, contacts);
    }
}
