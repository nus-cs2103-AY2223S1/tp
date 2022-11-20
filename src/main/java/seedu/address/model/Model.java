package seedu.address.model;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.model.message.Message;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.quote.Quote;
import seedu.address.model.reminder.ReadOnlyReminderList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns a {@code String} containing all tags for printing.
     */
    String printTagsPrettily();

    /**
     * Returns true if the tag exists in the address book.
     */
    boolean hasTag(Tag tag);

    /**
     * Removes the tags from the person.
     */
    void removeTags(Person target, Collection<Tag> tag);

    /**
     * Adds the tag to the address book.
     */
    void addTag(Tag tag);

    /**
     * Deletes the tags from the address book and returns the set of deleted tags.
     * Will also clear filters for the filtered list.
     */
    Set<Tag> deleteTags(Set<Tag> tag);

    /**
     * Adds the message to the address book.
     */
    void createMessage(Message message);

    /**
     * Deletes the message from the address book.
     * {@code message} must exist in the address book.
     */
    void deleteMessage(Message message);

    /**
     * Returns true if the address book contains {@code message.}
     */
    boolean hasMessage(Message message);

    /**
     * Returns an unmodifiable view of messages.
     */
    ObservableList<Message> getMessages();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Adds the filter given by {@code predicate} to the set of filters for the fitlered person list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void addNewFilterToFilteredPersonList(FilterCommandPredicate predicate);

    /**
     * Clears the set of filters in the fitlered person list.
     */
    void clearFiltersInFilteredPersonList();

    /**
     * Removes the filter given by {@code predicate} from the set of filters for the fitlered person list.
     */
    void removeFilterFromFilteredPersonList(FilterCommandPredicate predicate);

    /** Returns the list of tag filters applied. */
    Set<Predicate<Person>> getTagFilters();

    /** Returns the list of name filters applied. */
    Set<Predicate<Person>> getNameFilters();

    /** Returns the target person */
    TargetPerson getObservableTargetPerson();

    /**
     * Set the given person as target.
     * The person must exist in the address book.
     */
    void setTargetPerson(Person target);

    /**
     * Set target to none.
     */
    void clearTargetPerson();

    /** Returns {@code true} if person is target person, {@code false} otherwise */
    boolean isTargetPerson(Person person);

    /** Returns {@code true} if target person is present, {@code false} otherwise */
    boolean hasTargetPerson();

    /** Returns the target {@code Person} */
    Person getTargetPerson();

    /** Returns a random quote for the current session.*/
    Quote getQuote();

    /** Returns list of reminders */
    ReadOnlyReminderList getReminderList();

    /** Returns an unmodifiable view of the list of reminders */
    ObservableList<Reminder> getReminderListAsObservableList();

    /** Returns an unmodifiable view of the list of reminders for target person */
    ObservableList<Reminder> getTargetPersonReminderListAsObservableList();

    /**
     * Deletes the given reminder.
     * The reminder must exist in the address book.
     */
    void deleteReminder(Reminder reminder);

    /**
     * Adds the given reminder.
     * {@code Reminder} must not already exist in the address book.
     */
    void addReminder(Reminder reminder);

    /** Returns {@code true} if reminder already exists, {@code false} otherwise */
    boolean reminderExists(Reminder reminder);

    /**
     * Clears the currently displayed reminder list.
     */
    ObservableList<Reminder> getCurrentReminderList();

    /**
     * Clears the currently displayed reminder list.
     */
    void clearCurrentReminderList();

    /**
     * Clears all reminders
     */
    void clearAllReminders();
}
