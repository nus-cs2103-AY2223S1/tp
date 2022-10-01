package seedu.guest.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.guest.commons.core.GuiSettings;
import seedu.guest.model.guest.Guest;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Guest> PREDICATE_SHOW_ALL_GUESTS = unused -> true;

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
     * Returns the user prefs' guest book file path.
     */
    Path getGuestBookFilePath();

    /**
     * Sets the user prefs' guest book file path.
     */
    void setGuestBookFilePath(Path guestListFilePath);

    /**
     * Replaces guest book data with the data in {@code guestList}.
     */
    void setGuestBook(ReadOnlyGuestBook guestList);

    /** Returns the GuestList */
    ReadOnlyGuestBook getGuestBook();

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the guest book.
     */
    boolean hasGuest(Guest guest);

    /**
     * Deletes the given guest.
     * The guest must exist in the guest book.
     */
    void deleteGuest(Guest target);

    /**
     * Adds the given guest.
     * {@code guest} must not already exist in the guest book.
     */
    void addGuest(Guest guest);

    /**
     * Replaces the given guest {@code target} with {@code editedGuest}.
     * {@code target} must exist in the guest book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the guest book.
     */
    void setGuest(Guest target, Guest editedGuest);

    /** Returns an unmodifiable view of the filtered guest list */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Updates the filter of the filtered guest book to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGuestList(Predicate<Guest> predicate);
}
