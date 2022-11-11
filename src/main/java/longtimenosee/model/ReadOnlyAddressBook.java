package longtimenosee.model;

import javafx.collections.ObservableList;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Policy> getPolicyList();

    ObservableList<Event> getEventList();
}
