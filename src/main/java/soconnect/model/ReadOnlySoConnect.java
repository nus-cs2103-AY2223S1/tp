package soconnect.model;

import javafx.collections.ObservableList;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;

/**
 * Unmodifiable view of an SoConnect.
 */
public interface ReadOnlySoConnect {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

}
