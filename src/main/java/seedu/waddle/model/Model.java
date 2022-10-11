package seedu.waddle.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Itinerary> PREDICATE_SHOW_ALL_ITINERARIES = unused -> true;

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
    Path getWaddleFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setWaddleFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setWaddle(ReadOnlyWaddle waddle);

    /** Returns the AddressBook */
    ReadOnlyWaddle getWaddle();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasItinerary(Itinerary itinerary);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteItinerary(Itinerary target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addItinerary(Itinerary itinerary);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setItinerary(Itinerary target, Itinerary editedItinerary);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Itinerary> getFilteredItineraryList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItineraryList(Predicate<Itinerary> predicate);
}
