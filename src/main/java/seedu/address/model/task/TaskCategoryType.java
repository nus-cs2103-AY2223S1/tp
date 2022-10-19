package seedu.address.model.task;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum for task category types.
 */
public enum TaskCategoryType {
    DATABASE("database"),
    BACKEND("backend"),
    FRONTEND("frontend"),
    UIUX("uiux"),
    PRESENTATION("presentation"),
    OTHERS("others");

    private final String name;

    TaskCategoryType(String name) {
        this.name = name;
    }

    /**
     * Returns String of valid task categories as user input arguments
     *
     * @return String of valid task categories
     */
    public static String getValidTaskCategories() {
        StringBuilder str = new StringBuilder();
        for (TaskCategoryType c : TaskCategoryType.values()) {
            str.append(" ").append(c.name);
        }
        return str.toString();
    }

    /**
     * Looks up a {@code TaskCategoryType} from a given string.
     *
     * @param categoryName string to test
     * @return Empty if {@code categoryName} is not a valid task category type,
     *     else the corresponding {@code TaskCategoryType}
     */
    public static Optional<TaskCategoryType> getFromString(String categoryName) {
        return Arrays.stream(TaskCategoryType.values())
                .filter(priority -> priority.name.equals(categoryName))
                .findFirst();
    }

}

