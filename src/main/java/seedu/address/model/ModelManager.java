package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.model.message.Message;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.quote.Quote;
import seedu.address.model.quote.QuoteList;
import seedu.address.model.reminder.ReadOnlyReminderList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final ReminderList reminderList;
    private final ReminderList targetPersonReminderList;
    private final Set<Predicate<Person>> namePredicates;
    private final Set<Predicate<Person>> tagPredicates;
    private final FilteredList<Person> filteredPersons;
    private final TargetPerson targetPerson;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyReminderList reminderList) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs "
                + userPrefs + " and reminder list " + reminderList);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.reminderList = new ReminderList(reminderList);
        this.targetPersonReminderList = new ReminderList();
        this.namePredicates = new HashSet<>();
        this.tagPredicates = new HashSet<>();
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.targetPerson = new TargetPerson();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new ReminderList());
    }

    // =========== UserPrefs ======================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // =========== AddressBook ====================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        reminderList.deleteRemindersWithNameAndPhone(target.getName(), target.getPhone());

        if (isTargetPerson(target)) {
            clearTargetPerson();
        }
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        clearFiltersInFilteredPersonList();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
        updateReminderLists(target, editedPerson);

        if (isTargetPerson(target)) {
            setTargetPerson(editedPerson);
        }
    }

    // =========== Tags ===========================================================

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }

    @Override
    public void addTag(Tag tag) {
        requireNonNull(tag);
        addressBook.createTag(tag);
    }

    @Override
    public Set<Tag> deleteTags(Set<Tag> tags) {
        requireNonNull(tags);
        clearFiltersInFilteredPersonList();
        Set<Tag> deletedTags = addressBook.deleteTags(tags);
        return deletedTags;
    }

    @Override
    public void removeTags(Person target, Collection<Tag> tags) {
        Person untaggedPerson = addressBook.removeTags(target, tags);
        clearFiltersInFilteredPersonList();

        if (isTargetPerson(target)) {
            setTargetPerson(untaggedPerson);
        }
    }

    // =========== Message Templates ===========================================================

    @Override
    public void createMessage(Message message) {
        requireNonNull(message);
        addressBook.createMessage(message);
    }

    @Override
    public void deleteMessage(Message message) {
        requireNonNull(message);
        addressBook.deleteMessage(message);
    }

    @Override
    public boolean hasMessage(Message message) {
        requireNonNull(message);
        return addressBook.hasMessage(message);
    }

    @Override
    public ObservableList<Message> getMessages() {
        return addressBook.getMessageTemplates();
    }

    // =========== Filtered Person List ===========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void addNewFilterToFilteredPersonList(FilterCommandPredicate predicate) {
        requireNonNull(predicate);
        assert isAnyNonNull(predicate.getNamePredicate(), predicate.getTagPredicate());
        predicate.addNameFiltersToSet(namePredicates);
        predicate.addTagFiltersToSet(tagPredicates);
        updateFilteredPersonList();
    }

    @Override
    public void clearFiltersInFilteredPersonList() {
        namePredicates.clear();
        tagPredicates.clear();
        updateFilteredPersonList();
    }

    @Override
    public void removeFilterFromFilteredPersonList(FilterCommandPredicate predicate) {
        requireNonNull(predicate);
        assert isAnyNonNull(predicate.getNamePredicate(), predicate.getTagPredicate());
        predicate.removeNameFiltersFromSet(namePredicates);
        predicate.removeTagFiltersFromSet(tagPredicates);
        updateFilteredPersonList();
    }

    private void updateFilteredPersonList() {
        Predicate<Person> namePredicate = namePredicates.size() == 0 ? PREDICATE_SHOW_ALL_PERSONS
                : namePredicates.stream()
                        .reduce(notused -> false, (pred1, pred2) -> pred1.or(pred2));
        Predicate<Person> tagPredicate = tagPredicates.size() == 0 ? PREDICATE_SHOW_ALL_PERSONS
                : tagPredicates.stream()
                        .reduce(notused -> false, (pred1, pred2) -> pred1.or(pred2));
        filteredPersons.setPredicate(namePredicate.and(tagPredicate));
    }

    @Override
    public Set<Predicate<Person>> getTagFilters() {
        return Set.copyOf(this.tagPredicates);
    }

    @Override
    public Set<Predicate<Person>> getNameFilters() {
        return Set.copyOf(this.namePredicates);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook) && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    // =========== Target Person Accessors =============================================================
    @Override
    public ObservableList<Person> getTargetPersonAsObservableList() {
        return targetPerson.getAsObservableList();
    }

    @Override
    public void setTargetPerson(Person person) {
        requireNonNull(person);
        targetPerson.set(person);
        targetPersonReminderList.setReminders(
                reminderList.getRemindersWithNameAndPhone(person.getName(), person.getPhone()));
    }

    @Override
    public void clearTargetPerson() {
        targetPerson.clear();
        targetPersonReminderList.clear();
    }

    @Override
    public boolean isTargetPerson(Person person) {
        return targetPerson.isSamePerson(person);
    }

    @Override
    public boolean hasTargetPerson() {
        return targetPerson.isPresent();
    }

    @Override
    public Person getTargetPerson() {
        return targetPerson.get();
    }

    // =========== Motivational Quotes =================================================================
    @Override
    public Quote getQuote() {
        return QuoteList.getRandomQuote();
    }
    // =========== Reminder ====================================================================

    @Override
    public ReadOnlyReminderList getReminderList() {
        return reminderList;
    }

    @Override
    public ObservableList<Reminder> getReminderListAsObservableList() {
        return reminderList.getAllReminders();
    }

    @Override
    public ObservableList<Reminder> getTargetPersonReminderListAsObservableList() {
        return targetPersonReminderList.getAllReminders();
    }

    private boolean isTargetPersonReminder(Reminder reminder) {
        return hasTargetPerson()
                && reminder.matchesNameAndPhone(targetPerson.get().getName(), targetPerson.get().getPhone());
    }

    @Override
    public void deleteReminder(Reminder reminder) {
        reminderList.delete(reminder);
        if (isTargetPersonReminder(reminder)) {
            targetPersonReminderList.delete(reminder);
        }
    }

    @Override
    public void addReminder(Reminder reminder) {
        reminderList.add(reminder);
        if (isTargetPersonReminder(reminder)) {
            targetPersonReminderList.add(reminder);
        }
    }

    @Override
    public boolean reminderExists(Reminder reminder) {
        return reminderList.contains(reminder);
    }

    @Override
    public ObservableList<Reminder> getCurrentReminderList() {
        if (targetPerson.isPresent()) {
            return getTargetPersonReminderListAsObservableList();
        } else {
            return getReminderListAsObservableList();
        }
    }

    @Override
    public void clearCurrentReminderList() {
        if (targetPerson.isPresent()) {
            reminderList.deleteRemindersWithNameAndPhone(targetPerson.get().getName(), targetPerson.get().getPhone());
            targetPersonReminderList.clear();
        } else {
            reminderList.clear();
        }
    }

    private void updateReminderLists(Person target, Person editedPerson) {
        reminderList.updateRemindersWithNewNameAndPhone(target.getName(), target.getPhone(),
                editedPerson.getName(), editedPerson.getPhone());
        targetPersonReminderList.updateRemindersWithNewNameAndPhone(target.getName(), target.getPhone(),
                editedPerson.getName(), editedPerson.getPhone());
    }

    @Override
    public void clearAllReminders() {
        targetPersonReminderList.clear();
        reminderList.clear();
    }
}
