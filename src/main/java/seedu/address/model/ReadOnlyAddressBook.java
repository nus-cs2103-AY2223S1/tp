package seedu.address.model;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.message.Message;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tag set
     */
    Set<Tag> getTags();

    /**
     * Returns an unmodifiable view of the message list
     */
    List<Message> getMessageTemplates();
}
