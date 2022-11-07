package seedu.waddle.model;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Represents the in-memory model of Waddle data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Waddle waddle;
    private final UserPrefs userPrefs;
    private final FilteredList<Itinerary> filteredItineraries;

    /**
     * Initializes a ModelManager with the given waddle and userPrefs.
     */
    public ModelManager(ReadOnlyWaddle waddle, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(waddle, userPrefs);

        logger.fine("Initializing with Waddle: " + waddle + " and user prefs " + userPrefs);

        this.waddle = new Waddle(waddle);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItineraries = new FilteredList<>(this.waddle.getItineraryList());
    }

    public ModelManager() {
        this(new Waddle(), new UserPrefs());
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
    public Path getWaddleFilePath() {
        return userPrefs.getWaddleFilePath();
    }

    @Override
    public void setWaddleFilePath(Path waddleFilePath) {
        requireNonNull(waddleFilePath);
        userPrefs.setWaddleFilePath(waddleFilePath);
    }

    //=========== Waddle ================================================================================

    @Override
    public void setWaddle(ReadOnlyWaddle waddle) {
        this.waddle.resetData(waddle);
    }

    @Override
    public ReadOnlyWaddle getWaddle() {
        return waddle;
    }

    @Override
    public boolean hasItinerary(Itinerary itinerary) {
        requireNonNull(itinerary);
        return waddle.hasItinerary(itinerary);
    }

    @Override
    public void deleteItinerary(Itinerary target) {
        waddle.removeItinerary(target);
    }

    @Override
    public void addItinerary(Itinerary itinerary) {
        waddle.addItinerary(itinerary);
        updateFilteredItineraryList(PREDICATE_SHOW_ALL_ITINERARIES);
    }

    @Override
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireAllNonNull(target, editedItinerary);

        waddle.setItinerary(target, editedItinerary);
    }

    //=========== Filtered Itinerary List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Itinerary} backed by the internal list of
     * {@code versionedWaddle}
     */
    @Override
    public ObservableList<Itinerary> getFilteredItineraryList() {
        return filteredItineraries;
    }

    @Override
    public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
        requireNonNull(predicate);
        filteredItineraries.setPredicate(predicate);
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
        return waddle.equals(other.waddle)
                && userPrefs.equals(other.userPrefs)
                && filteredItineraries.equals(other.filteredItineraries);
    }

}
