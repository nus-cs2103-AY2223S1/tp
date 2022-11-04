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
     * Returns the user prefs' Waddle file path.
     */
    Path getWaddleFilePath();

    /**
     * Sets the user prefs' Waddle file path.
     */
    void setWaddleFilePath(Path waddleFilePath);

    /**
     * Replaces Waddle data with the data in {@code waddle}.
     */
    void setWaddle(ReadOnlyWaddle waddle);

    /** Returns Waddle */
    ReadOnlyWaddle getWaddle();

    /**
     * Returns true if a itinerary with the same identity as {@code itinerary} exists in Waddle.
     */
    boolean hasItinerary(Itinerary itinerary);

    /**
     * Deletes the given itinerary.
     * The itinerary must exist in Waddle.
     */
    void deleteItinerary(Itinerary target);

    /**
     * Adds the given itinerary.
     * {@code itinerary} must not already exist in Waddle.
     */
    void addItinerary(Itinerary itinerary);

    /**
     * Replaces the given itinerary {@code target} with {@code editedItinerary}.
     * {@code target} must exist in Waddle.
     * The itinerary identity of {@code editedItinerary} must not be the same as
     * another existing itinerary in Waddle
     */
    void setItinerary(Itinerary target, Itinerary editedItinerary);

    /** Returns an unmodifiable view of the filtered itinerary list */
    ObservableList<Itinerary> getFilteredItineraryList();

    /**
     * Updates the filter of the filtered itinerary list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItineraryList(Predicate<Itinerary> predicate);
}
