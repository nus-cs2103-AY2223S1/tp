package seedu.address.ui;

import seedu.address.model.person.Person;

public interface Observer {
    /**
     * Updates UI when model changes.
     * @param Person person that was updated
     */
    void updateUi(Person updatedPerson);
}
