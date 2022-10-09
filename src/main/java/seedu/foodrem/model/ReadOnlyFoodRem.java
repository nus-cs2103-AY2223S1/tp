package seedu.foodrem.model;

import javafx.collections.ObservableList;
import seedu.foodrem.model.item.Item;

/**
 * Unmodifiable view of FoodRem.
 */
public interface ReadOnlyFoodRem {

    /**
     * Returns an unmodifiable view of the item list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemList();

}
