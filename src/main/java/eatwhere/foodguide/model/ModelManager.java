package eatwhere.foodguide.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.commons.core.LogsCenter;
import eatwhere.foodguide.commons.util.CollectionUtil;
import eatwhere.foodguide.model.eatery.Eatery;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the food guide data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodGuide foodGuide;
    private final UserPrefs userPrefs;
    private final FilteredList<Eatery> filteredEateries;

    /**
     * Initializes a ModelManager with the given foodGuide and userPrefs.
     */
    public ModelManager(ReadOnlyFoodGuide foodGuide, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(foodGuide, userPrefs);

        logger.fine("Initializing with food guide: " + foodGuide + " and user prefs " + userPrefs);

        this.foodGuide = new FoodGuide(foodGuide);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEateries = new FilteredList<>(this.foodGuide.getEateryList());
    }

    public ModelManager() {
        this(new FoodGuide(), new UserPrefs());
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
    public Path getFoodGuideFilePath() {
        return userPrefs.getFoodGuideFilePath();
    }

    @Override
    public void setFoodGuideFilePath(Path foodGuideFilePath) {
        requireNonNull(foodGuideFilePath);
        userPrefs.setFoodGuideFilePath(foodGuideFilePath);
    }

    //=========== FoodGuide ================================================================================

    @Override
    public void setFoodGuide(ReadOnlyFoodGuide foodGuide) {
        this.foodGuide.resetData(foodGuide);
    }

    @Override
    public ReadOnlyFoodGuide getFoodGuide() {
        return foodGuide;
    }

    @Override
    public boolean hasEatery(Eatery eatery) {
        requireNonNull(eatery);
        return foodGuide.hasEatery(eatery);
    }

    @Override
    public void deleteEatery(Eatery target) {
        foodGuide.removeEatery(target);
    }

    @Override
    public void addEatery(Eatery eatery) {
        foodGuide.addEatery(eatery);
        updateFilteredEateryList(PREDICATE_SHOW_ALL_EATERIES);
    }

    @Override
    public void setEatery(Eatery target, Eatery editedEatery) {
        CollectionUtil.requireAllNonNull(target, editedEatery);

        foodGuide.setEatery(target, editedEatery);
    }

    //=========== Filtered Eatery List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Eatery} backed by the internal list of
     * {@code versionedFoodGuide}
     */
    @Override
    public ObservableList<Eatery> getFilteredEateryList() {
        return filteredEateries;
    }

    @Override
    public void updateFilteredEateryList(Predicate<Eatery> predicate) {
        requireNonNull(predicate);
        filteredEateries.setPredicate(predicate);
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
        return foodGuide.equals(other.foodGuide)
                && userPrefs.equals(other.userPrefs)
                && filteredEateries.equals(other.filteredEateries);
    }

}
