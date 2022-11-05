package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Assignment;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {
    public static final String DEFAULT_TITLE = "This is a title";
    public static final String DEFAULT_DESCRIPTION = "This is a description";
    public static final String DEFAULT_STUDENTS = "Adam, Ben, Chloe, Ethan";

    private TaskTitle title;
    private TaskDescription description;
    private List<String> students;
    private String type;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        title = new TaskTitle(DEFAULT_TITLE);
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        students = new ArrayList<>(Arrays.asList(DEFAULT_STUDENTS.split(", ")));
        type = "Assignment";
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public AssignmentBuilder(Task taskToCopy) {
        if (taskToCopy instanceof Assignment) {
            Assignment assignment = (Assignment) taskToCopy;
            title = assignment.getTitle();
            description = assignment.getDescription();
            students = assignment.getStudents();
        }

    }

    /**
     * Sets the {@code TaskTitle} of the {@code Task} that we are building.
     */
    public AssignmentBuilder withTitle(String title) {
        this.title = new TaskTitle(title);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public AssignmentBuilder withDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Students} of the {@code Task} that we are building.
     */
    public AssignmentBuilder withStudents(String students) {
        this.students = Arrays.asList(students.split(", "));
        return this;
    }

    public Task build() {
        return new Assignment(title, description, students);
    }
}
