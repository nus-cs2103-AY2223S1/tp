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
import seedu.foodrem.model.tag.Tag;

/**
 * Represents the in-memory model of FoodRem data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodRem foodRem;
    private final UserPrefs userPrefs;
    private final ObservableList<Item> itemsList;
    private final FilteredList<Item> filteredItems;
    private final FilteredList<Tag> filteredTags;
    private final SortedList<Item> sortedItems;

    /**
     * Initializes a ModelManager with the given foodRem and userPrefs.
     */
    public ModelManager(ReadOnlyFoodRem foodRem, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(foodRem, userPrefs);

        logger.fine("Initializing with RoodRem: " + foodRem + " and user prefs " + userPrefs);

        this.foodRem = new FoodRem(foodRem);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.foodRem.getItemList());
        sortedItems = new SortedList<>(filteredItems);
        itemsList = sortedItems;
        filteredTags = new FilteredList<>(this.foodRem.getTagList());
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
    public Path getFoodRemFilePath() {
        return userPrefs.getFoodRemFilePath();
    }

    @Override
    public void setFoodRemFilePath(Path foodRemFilePath) {
        requireNonNull(foodRemFilePath);
        userPrefs.setFoodRemFilePath(foodRemFilePath);
    }

    //=========== FoodRem ================================================================================

    @Override
    public ReadOnlyFoodRem getFoodRem() {
        return foodRem;
    }

    @Override
    public void setFoodRem(ReadOnlyFoodRem foodRem) {
        this.foodRem.resetData(foodRem);
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return foodRem.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        foodRem.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        foodRem.addItem(item);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);
        foodRem.setItem(target, editedItem);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return foodRem.hasTag(tag);
    }

    @Override
    public void deleteTag(Tag target) {
        foodRem.removeTag(target);
    }

    @Override
    public void addTag(Tag tag) {
        foodRem.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        foodRem.renameTag(target, editedTag);
    }

    //=========== Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedFoodRem}
     */
    @Override
    public ObservableList<Item> getCurrentList() {
        return itemsList;
    }

    @Override
    public void updateSortedItemList(Comparator<Item> comparator) {
        requireNonNull(comparator);
        sortedItems.setComparator(comparator);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Tag} backed by the internal list of
     * {@code versionedFoodRem}
     */
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ModelManager)) {
            return false;
        }
        ModelManager other = (ModelManager) obj;
        return foodRem.equals(other.foodRem)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems)
                && filteredTags.equals(other.filteredTags)
                && sortedItems.equals(other.sortedItems)
                && itemsList.equals(other.itemsList);
    }
}
