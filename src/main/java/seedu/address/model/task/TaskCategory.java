package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents the category of a task.
 */
public class TaskCategory {
    public static final String MESSAGE_CONSTRAINTS =
            "\"%s\" is not a valid category! Category name must be one of the following: "
                    + TaskCategoryType.getValidTaskCategories();
    private final TaskCategoryType taskCategoryType;

    /**
     * Constructor for TaskCategory
     */
    public TaskCategory(TaskCategoryType taskCategoryType) {
        requireNonNull(taskCategoryType);
        this.taskCategoryType = taskCategoryType;
    }

    /**
     * Returns the category of a task.
     *
     * @return The task category.
     */
    public TaskCategoryType getTaskCategoryType() {
        return this.taskCategoryType;
    }


    /**
     * Returns hashcode of the current object
     *
     * @return Hashcode of the object.
     */
    @Override
    public int hashCode() {
        return taskCategoryType.hashCode();
    }


    /**
     * Returns the string representation of the task category.
     *
     * @return Task priority.
     */
    @Override
    public String toString() {
        return taskCategoryType.name();
    }

    /**
     * Compares another object with the Category object.
     *
     * @param other The other object to be compared to.
     * @return If the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskCategory // instanceof handles nulls
                && ((TaskCategory) other).taskCategoryType.equals(this.taskCategoryType));
    }

    /**
     * Returns true if task category name is valid.
     *
     * @param test String to test.
     * @return Whether the String is a valid task category name.
     */
    public static boolean isValidTaskCategoryName(String test) {
        return TaskCategoryType.getFromString(test).isPresent();
    }

    /**
     * Returns the String form of a taskCategoryType.
     *
     * @return The taskCategoryType in String form.
     */
    public String getCategoryName() {
        return this.taskCategoryType.toString();
    }

}
