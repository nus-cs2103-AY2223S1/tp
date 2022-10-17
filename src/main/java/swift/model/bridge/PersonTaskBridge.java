package swift.model.bridge;

import static swift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.UUID;

/**
 * Represents a many-to-many relationship between Person and Task.
 */
public class PersonTaskBridge {
    private final UUID personId;
    private final UUID taskId;

    /**
     * Initializes PersonTaskBridge with the given personId and taskId.
     *
     * @param personId A unique identifier for the person.
     * @param taskId   A unique identifier for the task.
     */
    public PersonTaskBridge(UUID personId, UUID taskId) {
        requireAllNonNull(personId, taskId);
        this.personId = personId;
        this.taskId = taskId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PersonTaskBridge) {
            PersonTaskBridge other = (PersonTaskBridge) obj;
            return personId.equals(other.personId) && taskId.equals(other.taskId);
        }
        return false;
    }
}
