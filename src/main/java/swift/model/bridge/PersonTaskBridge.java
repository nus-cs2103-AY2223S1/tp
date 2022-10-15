package swift.model.bridge;

import static swift.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import swift.model.Model;
import swift.model.person.Person;

/**
 * Represents a many-to-many relationship between Person and Task.
 */
public class PersonTaskBridge {
    private final List<UUID> personIds;
    private final List<UUID> taskIds;

    /**
     * Initializes empty PersonTaskBridge.
     */
    public PersonTaskBridge() {
        this.personIds = new ArrayList<>();
        this.taskIds = new ArrayList<>();
    }

    /**
     * Initializes PersonTaskBridge with the given personIds and taskIds.
     *
     * @param personIds
     * @param taskIds
     */
    public PersonTaskBridge(List<UUID> personIds, List<UUID> taskIds) {
        requireAllNonNull(personIds, taskIds);
        this.personIds = personIds;
        this.taskIds = taskIds;
    }

    /**
     * Returns true if the given personId is in the PersonTaskBridge.
     *
     * @param person Person to check
     * @return true if the given personId is in the PersonTaskBridge
     */
    public boolean hasPerson(Person person) {
        return personIds.contains(person.getId());
    }

    /**
     * Returns a list of persons associated with this bridge.
     *
     * @param model Model to retrieve persons from.
     * @return List of persons associated with this bridge.
     */
    public List<Person> getPersons(Model model) {
        return model.getAddressBook().getPersonList().filtered(person -> personIds.contains(person.getId()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PersonTaskBridge) {
            PersonTaskBridge other = (PersonTaskBridge) obj;
            return personIds.equals(other.personIds) && taskIds.equals(other.taskIds);
        }
        return false;
    }
}
