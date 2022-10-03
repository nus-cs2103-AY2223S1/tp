package hobbylist.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.util.CollectionUtil;
import hobbylist.model.activity.Activity;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HobbyList hobbyList;
    private final UserPrefs userPrefs;
    private final FilteredList<Activity> filteredActivities;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyHobbyList addressBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.hobbyList = new HobbyList(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredActivities = new FilteredList<>(this.hobbyList.getPersonList());
    }

    public ModelManager() {
        this(new HobbyList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

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

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyHobbyList addressBook) {
        this.hobbyList.resetData(addressBook);
    }

    @Override
    public ReadOnlyHobbyList getAddressBook() {
        return hobbyList;
    }

    @Override
    public boolean hasPerson(Activity activity) {
        requireNonNull(activity);
        return hobbyList.hasPerson(activity);
    }

    @Override
    public void deletePerson(Activity target) {
        hobbyList.removePerson(target);
    }

    @Override
    public void addPerson(Activity activity) {
        hobbyList.addPerson(activity);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Activity target, Activity editedActivity) {
        CollectionUtil.requireAllNonNull(target, editedActivity);

        hobbyList.setPerson(target, editedActivity);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Activity> getFilteredPersonList() {
        return filteredActivities;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Activity> predicate) {
        requireNonNull(predicate);
        filteredActivities.setPredicate(predicate);
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
        return hobbyList.equals(other.hobbyList)
                && userPrefs.equals(other.userPrefs)
                && filteredActivities.equals(other.filteredActivities);
    }

}
