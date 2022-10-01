package seedu.guest.model;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.guest.commons.core.GuiSettings;
import seedu.guest.commons.core.LogsCenter;
import seedu.guest.model.guest.Guest;

/**
 * Represents the in-memory model of the guest book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final GuestBook guestBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Guest> filteredGuests;

    /**
     * Initializes a ModelManager with the given guestList and userPrefs.
     */
    public ModelManager(ReadOnlyGuestBook guestList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(guestList, userPrefs);

        logger.fine("Initializing with guest book: " + guestList + " and user prefs " + userPrefs);

        this.guestBook = new GuestBook(guestList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredGuests = new FilteredList<>(this.guestBook.getGuestList());
    }

    public ModelManager() {
        this(new GuestBook(), new UserPrefs());
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
    public Path getGuestBookFilePath() {
        return userPrefs.getGuestBookFilePath();
    }

    @Override
    public void setGuestBookFilePath(Path guestListFilePath) {
        requireNonNull(guestListFilePath);
        userPrefs.setGuestBookFilePath(guestListFilePath);
    }

    //=========== GuestList ================================================================================

    @Override
    public void setGuestBook(ReadOnlyGuestBook guestList) {
        this.guestBook.resetData(guestList);
    }

    @Override
    public ReadOnlyGuestBook getGuestBook() {
        return guestBook;
    }

    @Override
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guestBook.hasGuest(guest);
    }

    @Override
    public void deleteGuest(Guest target) {
        guestBook.removeGuest(target);
    }

    @Override
    public void addGuest(Guest guest) {
        guestBook.addGuest(guest);
        updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
    }

    @Override
    public void setGuest(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);

        guestBook.setGuest(target, editedGuest);
    }

    //=========== Filtered Guest List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Guest} backed by the internal list of
     * {@code versionedGuestList}
     */
    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        return filteredGuests;
    }

    @Override
    public void updateFilteredGuestList(Predicate<Guest> predicate) {
        requireNonNull(predicate);
        filteredGuests.setPredicate(predicate);
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
        return guestBook.equals(other.guestBook)
                && userPrefs.equals(other.userPrefs)
                && filteredGuests.equals(other.filteredGuests);
    }

}
