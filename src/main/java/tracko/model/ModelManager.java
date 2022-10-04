package tracko.model;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import tracko.commons.core.GuiSettings;
import tracko.commons.core.LogsCenter;
import tracko.model.order.Order;
import tracko.model.person.Person;

/**
 * Represents the in-memory model of the application data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TrackO trackO;
    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTrackO trackO, ReadOnlyUserPrefs userPrefs) {
        // After iteration, remove addressbook-related data/fields
        requireAllNonNull(trackO, userPrefs);

        logger.fine("Initializing with TrackO: " + trackO + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.trackO = new TrackO(trackO);
    }

    public ModelManager() {
        this(new TrackO(), new UserPrefs());
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

    public Path getOrdersFilePath() {
        return userPrefs.getOrdersFilePath();
    }

    public void setOrdersFilePath(Path ordersFilePath) {
        requireNonNull(ordersFilePath);
        userPrefs.setOrdersFilePath(ordersFilePath);
    }

    // TODO: add items file path related methods

    //=========== TrackO ==============================================================================

    // TODO: add items related methods

    @Override
    public void setTrackO(ReadOnlyTrackO trackO) {
        this.trackO.resetData(trackO);
    }

    @Override
    public ReadOnlyTrackO getTrackO() {
        return trackO;
    }

    @Override
    public void addOrder(Order order) {
        trackO.addOrder(order);
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return trackO.getOrderList();
    }
}
