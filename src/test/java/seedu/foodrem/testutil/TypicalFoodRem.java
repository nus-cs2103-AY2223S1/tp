package seedu.foodrem.testutil;

import static seedu.foodrem.testutil.TypicalItems.getTypicalItems;
import static seedu.foodrem.testutil.TypicalTags.getTypicalTags;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

/**
 * A utility class containing a list of {@code item} and {@code tag} objects to be used in tests.
 */
public class TypicalFoodRem {
    /**
     * Returns a {@code FoodRem} with all the typical items and tags.
     */
    public static FoodRem getTypicalFoodRem() {
        FoodRem foodRem = new FoodRem();
        for (Item item : getTypicalItems()) {
            Item i = new ItemBuilder(item).build();
            foodRem.addItem(i);
        }

        for (Tag tag : getTypicalTags()) {
            foodRem.addTag(tag);
        }
        return foodRem;
    }

    /**
     * Returns a {@code FoodRem} with all the typical items only.
     */
    public static FoodRem getFoodRemWithTypicalItems() {
        FoodRem foodRem = new FoodRem();
        for (Item item : getTypicalItems()) {
            foodRem.addItem(item);
        }

        return foodRem;
    }

    /**
     * Returns a {@code FoodRem} with all the typical tags only.
     */
    public static FoodRem getFoodRemWithTypicalTags() {
        FoodRem foodRem = new FoodRem();
        for (Tag tag : getTypicalTags()) {
            foodRem.addTag(tag);
        }

        return foodRem;
    }
}
