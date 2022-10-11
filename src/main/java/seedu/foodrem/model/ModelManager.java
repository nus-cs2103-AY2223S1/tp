package seedu.foodrem.model;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.model.item.Item;

/**
 * Represents the in-memory model of FoodRem data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodRem addressBook;
    private final UserPrefs userPrefs;
    private final ObservableList<Item> itemsList;
    private final FilteredList<Item> filteredItems;
    private final SortedList<Item> sortedItems;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyFoodRem addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new FoodRem(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.addressBook.getItemList());
        sortedItems = new SortedList<>(filteredItems);
        itemsList = sortedItems;
    }

    public ModelManager() {
        this(new FoodRem(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
    public ReadOnlyFoodRem getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyFoodRem addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return addressBook.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        addressBook.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        addressBook.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        addressBook.setItem(target, editedItem);
    }

    //=========== Sorted Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedAddressBook} according to a Comparator.
     */
    @Override
    public ObservableList<Item> getSortedItemList() {
        return sortedItems;
    }

    @Override
    public void updateSortedItemList(Comparator<Item> comparator) {
        requireNonNull(comparator);
        sortedItems.setComparator(comparator);
    }


    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedAddressBook}
     */
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
                && filteredItems.equals(other.filteredItems)
                && sortedItems.equals(other.sortedItems)
                && itemsList.equals(other.itemsList);
    }

    @Override
    public ObservableList<Item> getFilteredSortedItemList() {
        return itemsList;
    }

}
