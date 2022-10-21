package seedu.address.model.task;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task {
    public static final String IS_DONE_MESSAGE_CONSTRAINT = "IsDone can only be true or false in String form";
    private static final String NO_PERSON_ASSIGNED = "NONE";
    private static final String IS_DONE_TRUE_STRING = "true";
    private static final String IS_DONE_FALSE_STRING = "false";

    // Identity fields
    private TaskName name;
    private TaskCategory category;
    private Description description;

    // Data fields
    private Priority priority;
    private TaskDeadline deadline;
    private Email personEmailAddress;

    private Name personName;
    private boolean isDone;

    /**
     * Constructor method to instantiate a Task object. Every field must be present and not null.
     *
     * @param name               Name of task
     * @param category           Category of task
     * @param desc               Description of task
     * @param priority           Priority of task
     * @param deadline           Deadline of task
     * @param personEmailAddress Email address of person assigned to this task
     * @param status             status of task
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
        this.personName = null;
        isDone = status;
    }

    /**
     * Returns the String representation of isDone.
     *
     * @param isDone in Boolean form.
     * @return isDone in String form.
     */
    public static String covertIsDoneFromBooleanToString(boolean isDone) {
        return isDone ? IS_DONE_TRUE_STRING : IS_DONE_FALSE_STRING;
    }

    /**
     * Returns the Boolean representation of isDone.
     *
     * @param isDone in String form.
     * @return isDone in Boolean form.
     */
    public static Boolean covertIsDoneFromStringToBoolean(String isDone) {
        return isDone == IS_DONE_TRUE_STRING;
    }

    /**
     * Returns true if test String is a valid isDone.
     *
     * @param test String to test.
     * @return Whether the String is a valid isDone value.
     */
    public static boolean isValidIsDone(String test) {
        return test == IS_DONE_TRUE_STRING || test == IS_DONE_FALSE_STRING;
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
     * Setter method for Name field.
     *
     * @param newName new Name for this Task object
     */
    public void setName(TaskName newName) {
        name = newName;
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
     * Setter method for Description field.
     *
     * @param newDesc new Description for this Task object
     */
    public void setDescription(Description newDesc) {
        description = newDesc;
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
     * Setter method for Priority field.
     *
     * @param newPriority new Priority for this Task object
     */
    public void setPriority(Priority newPriority) {
        priority = newPriority;
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
     * Setter method for Category field.
     *
     * @param newCategory new Category for this Task
     */
    public void setCategory(TaskCategory newCategory) {
        category = newCategory;
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
     * Setter method for Deadline field.
     *
     * @param newDeadline new Deadline for this Task
     */
    public void setDeadline(TaskDeadline newDeadline) {
        deadline = newDeadline;
    }

    /**
     * Returns the Email Address of the person assigned to this Task.
     *
     * @return assigned to this Task
     */
    public Email getPersonEmailAddress() {
        return personEmailAddress;
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
     * Returns the name of the person assigned to this task
     *
     * @return the name of the person assigned to this task
     */
    public Name getPersonName() {
        return this.personName;
    }

    public void setPersonName(Name personName) {
        this.personName = personName;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void updateName(String name) {
        this.personName = new Name(name);
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
                && (otherTask.isDone() == (this.isDone()));
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

    /**
     * Returns a new copy of the Task
     *
     * @return a copy of the task.
     */

    public Task copy() {
        TaskName copyName = new TaskName(this.name.toString());
        TaskCategory copyCat = new TaskCategory(this.category.getLevel(), this.category.getTaskCategoryType());
        Description copyDescription = new Description(this.description.toString());
        Priority copyPriority = new Priority(this.getPriority().getPriority());
        TaskDeadline copyDeadline = new TaskDeadline(this.deadline.getDeadline());
        Email copyEmail = new Email(this.personEmailAddress.toString());
        Boolean copyIsDone = this.isDone;
        Task copy = new Task(copyName, copyDescription, copyPriority, copyCat, copyDeadline, copyEmail, copyIsDone);
        return copy;
    }

    public static String convertIsDoneFromBooleanToString(boolean isDone) {
        return isDone ? IS_DONE_TRUE_STRING : IS_DONE_FALSE_STRING;
    }

    /**
     * Returns the Boolean representation of isDone.
     *
     * @param isDone in String form.
     * @return isDone in Boolean form.
     */

}
