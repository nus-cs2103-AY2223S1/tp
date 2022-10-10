package seedu.foodrem.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.model.item.Item;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

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
    Path getFoodRemFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setFoodRemFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyFoodRem getFoodRem();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setFoodRem(ReadOnlyFoodRem addressBook);

    /**
     * Returns true if an item with the same identity as {@code Item} exists in FoodRem.
     */
    boolean hasItem(Item item);

    /**
     * Deletes the given item.
     * The item must exist in FoodRem.
     */
    void deleteItem(Item target);

    /**
     * Adds the given item.
     * {@code item} must not already exist in FoodRem.
     */
    void addItem(Item item);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in FoodRem.
     * The item identity of {@code editedItem} must not be the same as another existing item in FoodRem.
     */
    void setItem(Item target, Item editedItem);

    /**
     * Returns an unmodifiable view of the filtered item list
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);
}
