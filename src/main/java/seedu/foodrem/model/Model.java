package seedu.foodrem.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

    /**
     * Returns true if a tag with the same name as {@code tag} exists in the address book.
     */
    boolean hasTag(Tag tag);

    /**
     * Deletes the given tag.
     * The tag must exist in the address book.
     */
    void deleteTag(Tag target);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the address book.
     */
    void addTag(Tag tag);

    /**
     * Replaces the given tag {@code target} with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The tag name of {@code editedTag} must not be the same as another existing tag in the address book.
     */
    void setTag(Tag target, Tag editedTag);

    /**
     * Returns an unmodifiable view of the filtered tag list
     */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Updates the filter of the filtered tag list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTagList(Predicate<Tag> predicate);

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
     * Returns an unmodifiable view of the filtered item list.
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Returns an unmodifiable view of the filtered and sorted item list.
     */
    ObservableList<Item> getFilteredSortedItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    /**
     * Returns an unmodifiable view of the sorted item list.
     */
    ObservableList<Item> getSortedItemList();

    /**
     * Updates the sorter of the sorted item list to filter by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedItemList(Comparator<Item> comparator);
}
