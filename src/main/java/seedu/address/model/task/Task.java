package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task {
    private static final String NO_PERSON_ASSIGNED = "NONE";

    // Identity fields
    private TaskName name;
    private TaskCategory category;
    private Description description;

    // Data fields
    private Priority priority;
    private TaskDeadline deadline;
    private Email personEmailAddress;
    private boolean isDone;

    /**
     * Constructor method to instantiate a Task object. Every field must be present and not null.
     *
     * @param name     Name of task
     * @param category Category of task
     * @param desc     Description of task
     * @param priority Priority of task
     * @param deadline Deadline of task
     * @param personEmailAddress Email address of person assigned to this task
     * @param status   status of task
     */
    public Task(TaskName name, Description desc, Priority priority, TaskCategory category,
                TaskDeadline deadline, Email personEmailAddress, boolean status) {
        requireAllNonNull(name, category, desc, priority, deadline, status);
        this.name = name;
        this.category = category;
        description = desc;
        this.priority = priority;
        this.deadline = deadline;
        this.personEmailAddress = personEmailAddress;
        isDone = status;
    }

    /**
     * Setter method for Name field.
     *
     * @param newName new Name for this Task object
     */
    public void setName(TaskName newName) {
        name = newName;
    }

    /**
     * Setter method for Description field.
     *
     * @param newDesc new Description for this Task object
     */
    public void setDescription(Description newDesc) {
        description = newDesc;
    }

    /**
     * Setter method for Priority field.
     *
     * @param newPriority new Priority for this Task object
     */
    public void setPriority(Priority newPriority) {
        priority = newPriority;
    }

    /**
     * Setter method for Category field.
     *
     * @param newCategory new Category for this Task
     */
    public void setCategory(TaskCategory newCategory) {
        category = newCategory;
    }

    /**
     * Setter method for Deadline field.
     *
     * @param newDeadline new Deadline for this Task
     */
    public void setDeadline(TaskDeadline newDeadline) {
        deadline = newDeadline;
    }

    /**
     * Setter method for personEmailAddress field.
     *
     * @param personEmailAddress Email of the new person assigned to this Task
     */
    public void setPersonEmailAddress(Email personEmailAddress) {
        this.personEmailAddress = personEmailAddress;
    }

    /**
     * Setter method for whether this Task has been completed.
     *
     * @param isDone true if this Task is done, and false otherwise
     */
    public void isDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns true if this Task is done, and false otherwise.
     *
     * @return true if this Task is done, and false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the Name of this Task.
     *
     * @return Name of this Task
     */
    public TaskName getName() {
        return name;
    }

    /**
     * Returns the Description of this Task.
     *
     * @return Description of this Task
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns the Priority of this Task.
     *
     * @return Priority of this Task
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns the Category of this Task.
     *
     * @return Category of this Task
     */
    public TaskCategory getCategory() {
        return category;
    }

    /**
     * Returns the Deadline of this Task.
     *
     * @return Deadline of this Task
     */
    public TaskDeadline getDeadline() {
        return deadline;
    }

    /**
     * Returns the Email Address of the person assigned to this Task.
     *
     * @return  assigned to this Task
     */
    public Email getPersonEmailAddress() {
        return personEmailAddress;
    }

    /**
     * Returns the name of the person assigned to this task
     *
     * @param persons List of persons associated with the address book
     * @return the name of the person assigned to this task
     */
    public String getPersonName(List<Person> persons) {
        String name = NO_PERSON_ASSIGNED;
        for (Person person: persons) {
            if (person.getEmail().equals(personEmailAddress)) {
                name = person.getName().toString();
            }
        }
        return name;
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     *
     * @param otherTask Task of comparison
     * @return true if both tasks are the same, and false otherwise
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null && otherTask.getName().equals(getName());
    }

    /**
     * Returns true if both tasks have the same fields.
     * This defines a stronger notion of equality between two tasks.
     *
     * @param other Object of comparison
     * @return true if both tasks have the same fields, and false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getCategory().equals(getCategory())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getPersonEmailAddress().equals(getPersonEmailAddress())
                && otherTask.getPriority().equals(getPriority())
                && (otherTask.isDone() && this.isDone());
    }

    /**
     * Method for custom fields hashing.
     *
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, priority, category, deadline, personEmailAddress, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Category: ")
                .append(getCategory())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Assigned to: ")
                .append(getPersonEmailAddress())
                .append("; Status: ");

        if (isDone) {
            builder.append("Done");
        } else {
            builder.append("Not done");
        }

        return builder.toString();
    }

}
