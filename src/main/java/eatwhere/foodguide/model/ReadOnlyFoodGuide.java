package eatwhere.foodguide.model;

import eatwhere.foodguide.model.eatery.Eatery;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a food guide
 */
public interface ReadOnlyFoodGuide {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Eatery> getEateryList();

}
