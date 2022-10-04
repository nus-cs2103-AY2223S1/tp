package gim.model;

import static java.util.Objects.requireNonNull;
import static gim.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import gim.commons.core.GuiSettings;
import gim.commons.core.LogsCenter;
import gim.model.exercise.Exercise;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Exercise> filteredExercises;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExercises = new FilteredList<>(this.addressBook.getExerciseList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasExercise(Exercise person) {
        requireNonNull(person);
        return addressBook.hasExercise(person);
    }

    @Override
    public void deleteExercise(Exercise target) {
        addressBook.removeExercise(target);
    }

    @Override
    public void addExercise(Exercise person) {
        addressBook.addExercise(person);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);

        addressBook.setExercise(target, editedExercise);
    }

    //=========== Filtered Exercise List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Exercise} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        filteredExercises.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredExercises.equals(other.filteredExercises);
    }

}
