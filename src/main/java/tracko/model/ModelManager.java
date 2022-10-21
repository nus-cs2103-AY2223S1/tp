package tracko.model;

import static java.util.Objects.requireNonNull;
import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import tracko.commons.core.GuiSettings;
import tracko.commons.core.LogsCenter;
import tracko.model.item.Item;
import tracko.model.order.Order;

/**
 * Represents the in-memory model of the application data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TrackO trackO;
    private final UserPrefs userPrefs;
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<Item> filteredItems;
    private final SortedList<Order> sortedOrders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTrackO trackO, ReadOnlyUserPrefs userPrefs) {
        // After iteration, remove addressbook-related data/fields
        requireAllNonNull(trackO, userPrefs);

        logger.fine("Initializing with TrackO: " + trackO + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.trackO = new TrackO(trackO);
        this.filteredOrders = new FilteredList<>(this.trackO.getOrderList());
        this.sortedOrders = new SortedList<>(filteredOrders);
        this.filteredItems = new FilteredList<>(this.trackO.getInventoryList());
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

    public Path getTrackOFilePath() {
        return userPrefs.getTrackOFilePath();
    }

    public void setTrackOFilePath(Path trackOFilePath) {
        requireNonNull(trackOFilePath);
        userPrefs.setTrackOFilePath(trackOFilePath);
    }

    // TODO: add items file path related methods

    //=========== TrackO ==============================================================================

    @Override
    public void setTrackO(ReadOnlyTrackO trackO) {
        this.trackO.resetData(trackO);
    }

    @Override
    public ReadOnlyTrackO getTrackO() {
        return trackO;
    }

    // ORDER METHODS ========================================================================================

    @Override
    public void addOrder(Order order) {
        trackO.addOrder(order);
    }

    @Override
    public void deleteOrder(Order order) {
        trackO.deleteOrder(order);
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return trackO.getOrderList();
    }

    // FILTERED ORDER LIST ACCESSORS ========================================================================

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code TrackO}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    //=========== Sorted Order List Accessors =============================================================

    @Override
    public ObservableList<Order> getSortedOrderList() {
        return sortedOrders;
    }

    @Override
    public void updateSortedOrderList(Comparator<Order> comparator) {
        requireNonNull(comparator);
        sortedOrders.setComparator(comparator);
    }

    // ITEM METHODS ==========================================================================================

    @Override
    public void addItem(Item item) {
        trackO.addItem(item);
    }

    @Override
    public Item getItem(String itemName) {
        return trackO.getItem(itemName);
    }

    @Override
    public void deleteItem(Item item) {
        trackO.deleteItem(item);
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return trackO.hasItem(item);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);
        trackO.setItem(target, editedItem);
    }

    // FILTERED ITEM LIST ACCESSORS ======================================================================

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public int getFilteredItemListSize() {
        return filteredItems.size();
    }

    @Override
    public ObservableList<Item> getInventoryList() {
        return trackO.getInventoryList();
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
        return trackO.equals(other.trackO)
            && userPrefs.equals(other.userPrefs);
    }
}
