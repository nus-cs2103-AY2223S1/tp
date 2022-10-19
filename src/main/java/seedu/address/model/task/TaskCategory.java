package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.model.task.exceptions.LevelOutOfRangeException;

/**
 * Represents the category of a task.
 */
public class TaskCategory {
    public static final Integer TASK_CATEGORY_LEVEL_LOWER_BOUND = 0;
    public static final Integer TASK_CATEGORY_LEVEL_UPPER_BOUND = 5;
    public static final String MESSAGE_CONSTRAINTS =
            "Category name must be one of the following: " + TaskCategoryType.getValidTaskCategories()
            + " and category level must be >= " + TASK_CATEGORY_LEVEL_LOWER_BOUND
            + " and <= " + TASK_CATEGORY_LEVEL_UPPER_BOUND;
    private final Integer level;
    private final TaskCategoryType taskCategoryType;

    /**
     * Constructor for TaskCategory
     */
    public TaskCategory(Integer level, TaskCategoryType taskCategoryType) throws LevelOutOfRangeException {
        if (level < TASK_CATEGORY_LEVEL_LOWER_BOUND || level > TASK_CATEGORY_LEVEL_UPPER_BOUND) {
            throw new LevelOutOfRangeException();
        }
        requireNonNull(taskCategoryType);
        this.level = level;
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
     * Returns the level of a task.
     *
     * @return The task level.
     */
    public int getLevel() {
        return this.level;
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
                && ((TaskCategory) other).level == this.level
                && ((TaskCategory) other).taskCategoryType.equals(this.taskCategoryType));
    }

    /**
     * Returns true if task category level is valid.
     *
     * @param test int to test.
     * @return Whether the int is a valid task category level.
     */
    public static boolean isValidTaskCategoryLevel(String test) {
        Integer value = Integer.parseInt(test);
        return value >= TASK_CATEGORY_LEVEL_LOWER_BOUND && value <= TASK_CATEGORY_LEVEL_UPPER_BOUND;
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
