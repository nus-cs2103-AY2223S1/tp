package eatwhere.foodguide.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.model.eatery.Eatery;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Eatery> PREDICATE_SHOW_ALL_EATERIES = unused -> true;

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

    /**
     * Returns the user prefs' food guide file path.
     */
    Path getFoodGuideFilePath();

    /**
     * Sets the user prefs' food guide file path.
     */
    void setFoodGuideFilePath(Path foodGuideFilePath);

    /**
     * Replaces food guide data with the data in {@code foodGuide}.
     */
    void setFoodGuide(ReadOnlyFoodGuide foodGuide);

    /** Returns the FoodGuide */
    ReadOnlyFoodGuide getFoodGuide();

    /**
     * Returns true if an eatery with the same identity as {@code eatery} exists in the food guide.
     */
    boolean hasEatery(Eatery eatery);

    /**
     * Deletes the given eatery.
     * The eatery must exist in the food guide.
     */
    void deleteEatery(Eatery target);

    /**
     * Adds the given eatery.
     * {@code eatery} must not already exist in the food guide.
     */
    void addEatery(Eatery eatery);

    /**
     * Replaces the given eatery {@code target} with {@code editedEatery}.
     * {@code target} must exist in the food guide.
     * The eatery identity of {@code editedEatery} must not be the same as another existing eatery in the food guide.
     */
    void setEatery(Eatery target, Eatery editedEatery);

    /** Returns an unmodifiable view of the filtered eatery list */
    ObservableList<Eatery> getFilteredEateryList();

    /**
     * Updates the filter of the filtered eatery list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEateryList(Predicate<Eatery> predicate);
}
