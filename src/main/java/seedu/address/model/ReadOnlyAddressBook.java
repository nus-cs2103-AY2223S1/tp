package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

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
     * Returns an unmodifiable view of the tasks list.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the tags list.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns the sort by deadline status of the task list.
     */
    Boolean isSortByDeadline();
}
