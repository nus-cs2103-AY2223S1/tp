package seedu.address.ui;

import seedu.address.model.person.Person;

/**
 * Observer interface for person updates in models.
 */
public interface Observer {
    /**
     * Updates UI when model changes.
     * @param updatedPerson person that was updated
     */
    void updateUi(Person updatedPerson);
}
