package seedu.foodrem.model;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.model.item.Item;

/**
 * Represents the in-memory model of FoodRem data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodRem foodRem;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a ModelManager with the given foodRem and userPrefs.
     */
    public ModelManager(ReadOnlyFoodRem foodRem, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(foodRem, userPrefs);

        logger.fine("Initializing with RoodRem: " + foodRem + " and user prefs " + userPrefs);

        this.foodRem = new FoodRem(foodRem);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.foodRem.getItemList());
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

    //=========== AddressBook ================================================================================

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
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        foodRem.setItem(target, editedItem);
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
        return foodRem.equals(other.foodRem)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }

}
