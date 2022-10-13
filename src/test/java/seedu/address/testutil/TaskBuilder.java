package seedu.address.testutil;

import java.time.LocalDate;
import java.util.HashSet;

import seedu.address.logic.task.Description;
import seedu.address.logic.task.Priority;
import seedu.address.logic.task.PriorityEnum;
import seedu.address.logic.task.Task;
import seedu.address.logic.task.TaskCategory;
import seedu.address.logic.task.TaskCategoryType;
import seedu.address.logic.task.TaskDeadline;
import seedu.address.logic.task.TaskName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;


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
    private Person taskPerson;
    private boolean taskStatus;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(defaultName);
        taskCategory = new TaskCategory(3, defaultCatType);
        taskDescription = new Description(defaultDescription);
        taskPriority = new Priority(defaultPriority);
        taskDeadline = new TaskDeadline(defaultLocalDate);
        taskPerson = new Person(new Name("test"), new Phone("99999999"),
                new Email("test@gmail.com"), new Address("test"), new HashSet());
        taskStatus = defaultStatus;
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
        taskStatus = taskToCopy.isDone();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new TaskName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public TaskBuilder withCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public TaskBuilder withDescription(String Description) {
        this.taskDescription = new Description(Description);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public TaskBuilder withPriority(PriorityEnum priority) {
        this.taskPriority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public TaskBuilder withDeadline(LocalDate date) {
        this.taskDeadline = new TaskDeadline(date);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public TaskBuilder withPerson(Person person) {
        this.taskPerson = person;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public TaskBuilder withStatus(boolean status) {
        this.taskStatus = status;
        return this;
    }

    /**
     * Builds a Task
     * @return the task to be builtF
     */

    public Task build() {
        return new Task(taskName, taskCategory, taskDescription, taskPriority, taskDeadline, taskPerson, taskStatus);
    }

}
