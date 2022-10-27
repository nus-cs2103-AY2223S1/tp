package seedu.address.ui;

import seedu.address.model.person.Person;

public interface Observer {
    /**
     * Updates UI when model changes.
     */
    void updateUi(Person updatedPerson);
}
