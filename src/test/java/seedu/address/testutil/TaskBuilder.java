package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_TASK_NAME = "Assignment 1";
    public static final String DEFAULT_TASK_DESCRIPTION = "Due by Mon";
    public static final String DEFAULT_TASK_DEADLINE = "10/10/2020";

    private TaskName taskName;
    private TaskDescription taskDescription;
    private TaskDeadline taskDeadline;
    private Set<Student> students;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_TASK_NAME);
        taskDescription = new TaskDescription(DEFAULT_TASK_DESCRIPTION);
        taskDeadline = new TaskDeadline(DEFAULT_TASK_DEADLINE);
        students = new HashSet<>();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
        taskDescription = taskToCopy.getTaskDescription();
        taskDeadline = taskToCopy.getTaskDeadline();
        students = new HashSet<>(taskToCopy.getStudents());
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Parses the {@code students} into a {@code Set<Student>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withStudents() {
        this.students = new HashSet<>(Arrays.asList(SampleDataUtil.getSampleStudents()));
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String taskDescription) {
        this.taskDescription = new TaskDescription(taskDescription);
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDeadline(String taskDeadline) {
        this.taskDeadline = new TaskDeadline(taskDeadline);
        return this;
    }

    public Task build() {
        return new Task(taskName, taskDescription, taskDeadline, students);
    }
}
