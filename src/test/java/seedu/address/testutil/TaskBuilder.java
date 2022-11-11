package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Project;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TITLE = "Add task functionality to Arrow";
    public static final boolean DEFAULT_IS_COMPLETED = false;

    private Title title;
    private boolean isCompleted;
    private Deadline deadline;
    private Project project;
    private Set<Contact> assignedContacts;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = new Title(DEFAULT_TITLE);
        isCompleted = DEFAULT_IS_COMPLETED;
        deadline = Deadline.UNSPECIFIED;
        project = Project.UNSPECIFIED;
        assignedContacts = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTitle();
        isCompleted = taskToCopy.getCompleted();
        deadline = taskToCopy.getDeadline();
        project = taskToCopy.getProject();
        assignedContacts = new HashSet<>(taskToCopy.getAssignedContacts());
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code isCompleted} of the {@code Task} that we are building.
     */
    public TaskBuilder withCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = Deadline.of(TaskParserUtil.convertStringToLocalDate(deadline));
        return this;
    }

    /**
     * Sets the {@code Project} of the {@code Task} that we are building.
     */
    public TaskBuilder withProject(String projectName) {
        if (!projectName.equals("")) {
            this.project = new Project(projectName);
        }
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withContacts(String... contacts) {
        this.assignedContacts = SampleDataUtil.getContactSet(contacts);
        return this;
    }


    public Task build() {
        return new Task(title, isCompleted, deadline, project, assignedContacts);
    }
}
