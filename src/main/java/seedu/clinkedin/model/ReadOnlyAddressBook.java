package seedu.clinkedin.model;

import java.util.Map;

import javafx.collections.ObservableList;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.tag.TagType;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    Map<Prefix, TagType> getPrefixMap();

    /**
     * Returns the number of persons in the clinkedin book.
     */
    int getCount();
}
