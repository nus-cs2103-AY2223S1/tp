package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;


/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

    private final String defaultName = "Test";
    private final TaskCategoryType defaultCatType = TaskCategoryType.OTHERS;
    private final String defaultDescription = "Test";
    private final PriorityEnum defaultPriority = PriorityEnum.MEDIUM;
    private final LocalDate defaultLocalDate = LocalDate.now();

    private final boolean defaultStatus = true;


    private TaskName taskName;
    private TaskCategory taskCategory;
    private Description taskDescription;
    private Priority taskPriority;
    private TaskDeadline taskDeadline;

    private Email taskPersonEmailAddress;

    private Person taskPerson;
    private boolean taskIsDone;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(defaultName);
        taskCategory = new TaskCategory(defaultCatType);
        taskDescription = new Description(defaultDescription);
        taskPriority = new Priority(defaultPriority);
        taskDeadline = new TaskDeadline(defaultLocalDate);
        taskPersonEmailAddress = new Email("test@gmail.com");
        taskIsDone = defaultStatus;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getName();
        taskCategory = taskToCopy.getCategory();
        taskDescription = taskToCopy.getDescription();
        taskPriority = taskToCopy.getPriority();
        taskDeadline = taskToCopy.getDeadline();
        taskPerson = taskToCopy.getPerson();
        taskIsDone = taskToCopy.isDone();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new TaskName(name);
        return this;
    }

    /**
     * ets the {@code Category} of the {@code Task} that we are building.
     */
    public TaskBuilder withCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(Description description) {
        this.taskDescription = description;
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Task} that we are building.
     */
    public TaskBuilder withPriority(PriorityEnum priority) {
        this.taskPriority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(LocalDate date) {
        this.taskDeadline = new TaskDeadline(date);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskPersonEmailAddressPerson(Email email) {
        this.taskPersonEmailAddress = email;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(boolean status) {
        this.taskIsDone = status;
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Task} that we are building.
     */
    public TaskBuilder withPerson(Person person) {
        this.taskPerson = person;
        return this;
    }

    /**
     * Builds a Task
     *
     * @return the task to be built
     */

    public Task build() {
        return new Task(taskName, taskDescription, taskPriority, taskCategory, taskDeadline,
                taskPerson, taskIsDone);
    }

}
