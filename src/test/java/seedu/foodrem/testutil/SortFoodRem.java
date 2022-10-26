package seedu.foodrem.testutil;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.item.Item;

/**
 * A utility class containing a list of {@code item} objects to be used for sorting related tests.
 */
public class SortFoodRem {
    /**
     * Returns a {@code FoodRem} with all the typical items and tags.
     */
    public static FoodRem getSortFoodRem() {
        FoodRem foodRem = new FoodRem();
        for (Item item : ItemsToSort.getSortItems()) {
            Item i = new ItemBuilder(item).build();
            foodRem.addItem(i);
        }
        return foodRem;
    }
}
