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
import tracko.model.item.InventoryItem;
import tracko.model.order.Order;

/**
 * Represents the in-memory model of the application data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TrackO trackO;
    private final UserPrefs userPrefs;
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<InventoryItem> filteredInventoryItems;
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
        this.filteredInventoryItems = new FilteredList<>(this.trackO.getInventoryList());
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

    @Override
    public void setOrder(Order orderToEdit, Order editedOrder) {
        requireAllNonNull(orderToEdit, editedOrder);
        trackO.setOrder(orderToEdit, editedOrder);
    }

    @Override
    public void markOrder(Order orderToMark, boolean isPaid, boolean isDelivered) {
        requireNonNull(orderToMark);
        trackO.markOrder(orderToMark, isPaid, isDelivered);
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
    public void addItem(InventoryItem inventoryItem) {
        trackO.addItem(inventoryItem);
    }

    @Override
    public InventoryItem getItem(String itemName) {
        return trackO.getItem(itemName);
    }

    @Override
    public void deleteItem(InventoryItem inventoryItem) {
        trackO.deleteItem(inventoryItem);
    }

    @Override
    public boolean hasItem(InventoryItem inventoryItem) {
        requireNonNull(inventoryItem);
        return trackO.hasItem(inventoryItem);
    }

    @Override
    public void setItem(InventoryItem target, InventoryItem editedInventoryItem) {
        requireAllNonNull(target, editedInventoryItem);
        trackO.setItem(target, editedInventoryItem);
    }

    // FILTERED ITEM LIST ACCESSORS ======================================================================

    @Override
    public ObservableList<InventoryItem> getFilteredItemList() {
        return filteredInventoryItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<InventoryItem> predicate) {
        requireNonNull(predicate);
        filteredInventoryItems.setPredicate(predicate);
    }

    @Override
    public int getFilteredItemListSize() {
        return filteredInventoryItems.size();
    }

    @Override
    public ObservableList<InventoryItem> getInventoryList() {
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

    // UI DATA REFRESHER ======================================================================

    @Override
    public void refreshData() {
        trackO.refreshInventoryData();
        trackO.refreshOrderData();
    }
}
