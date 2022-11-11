package tracko.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tracko.commons.core.GuiSettings;
import tracko.model.item.InventoryItem;
import tracko.model.order.Order;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<InventoryItem> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

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

    // TrackO =======================================================================================

    // TODO: add item related methods

    /**
     * Returns the user pref's orders file path.
     */
    Path getTrackOFilePath();

    /**
     * Sets the user pref's orders file path.
     */
    void setTrackOFilePath(Path trackOFilePath);

    /**
     * Replaces application data with the data in {@code trackO}
     */
    void setTrackO(ReadOnlyTrackO trackO);

    /** Returns the TrackO */
    ReadOnlyTrackO getTrackO();

    // ORDER METHODS =============================================================================

    /**
     * Adds the given order.
     */
    void addOrder(Order order);

    /**
     * Deletes the given order.
     */
    void deleteOrder(Order order);

    void setOrder(Order orderToEdit, Order editedOrder);

    void markOrder(Order orderToMark, boolean isPaid, boolean isDelivered);

    /**
     * Returns the order list.
     */
    ObservableList<Order> getOrderList();

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    // ITEM METHODS =============================================================================

    /**
     * Adds the given item.
     */
    void addItem(InventoryItem inventoryItem);

    /**
     * Returns an item from the inventory list with the given item name.
     */
    InventoryItem getItem(String itemName);

    /**
     * Deletes the given item.
     */
    void deleteItem(InventoryItem inventoryItem);

    /**
     * Returns true if an item with the same identity as {@code item} exists in the inventory list.
     */
    boolean hasItem(InventoryItem inventoryItem);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the inventory list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the inventory list.
     */
    void setItem(InventoryItem target, InventoryItem editedInventoryItem);

    /**
     * Returns an unmodifiable view of the filtered item list.
     */
    ObservableList<InventoryItem> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate given to filter the items in the item list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<InventoryItem> predicate);

    /**
     * Returns the number of items in the filtered item list.
     */
    int getFilteredItemListSize();

    /**
     * Returns an unmodifiable view of the sorted item list.
     */
    ObservableList<Order> getSortedOrderList();

    /**
     * Updates the comparator of the sorted item list to sort by the given {@code comparator}.
     *
     * @param comparator The comparator given to sort the items in the item list.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedOrderList(Comparator<Order> comparator);

    /**
     * Returns the item list.
     */
    ObservableList<InventoryItem> getInventoryList();

    void refreshData();
}
