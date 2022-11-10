package seedu.address.model.task;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the three levels of priority that a task in the task list can take.
 */
public enum PriorityEnum {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String name;

    PriorityEnum(String name) {
        this.name = name;
    }

    /**
     * Returns String of valid task priorities as user input arguments
     *
     * @return String of valid task priorities
     */
    public static String getValidPriorities() {
        StringBuilder str = new StringBuilder();
        for (PriorityEnum p : PriorityEnum.values()) {
            str.append(" ").append(p.name);
        }
        return str.toString();
    }

    /**
     * Looks up a {@code PriorityEnum} from a given string.
     *
     * @param priorityName string to test
     * @return Empty if {@code priorityName} is not a valid priority, else the corresponding {@code PriorityEnum}
     */
    public static Optional<PriorityEnum> getFromString(String priorityName) {
        return Arrays.stream(PriorityEnum.values())
                .filter(priority -> priority.name.equals(priorityName))
                .findFirst();
    }

    @Override
    public String toString() {
        return name;
    }
}
