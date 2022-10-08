package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.question.Question;
import seedu.address.model.tutorial.Tutorial;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Question> getQuestionList();

    /**
     * Returns an unmodifiable view of the tutorial list.
     * This list will not contain any duplicate tutorials.
     */
    ObservableList<Tutorial> getTutorialList();

}
