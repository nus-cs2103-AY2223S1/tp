package seedu.foodrem.testutil;

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
        for (Item item : TypicalItems.getTypicalItems()) {
            Item i = new ItemBuilder(item).build();
            foodRem.addItem(i);
        }

        for (Tag tag : TypicalTags.getTypicalTags()) {
            foodRem.addTag(tag);
        }
        return foodRem;
    }

    /**
     * Returns a {@code FoodRem} with all the typical items only.
     */
    public static FoodRem getFoodRemWithTypicalItems() {
        FoodRem foodRem = new FoodRem();
        for (Item item : TypicalItems.getTypicalItems()) {
            foodRem.addItem(item);
            for (Tag tag : item.getTagSet()) {
                if (!foodRem.hasTag(tag)) {
                    foodRem.addTag(tag);
                }
            }
        }
        return foodRem;
    }

    /**
     * Returns a {@code FoodRem} with all the typical items WITHOUT TAGS
     */
    public static FoodRem getFoodRemWithTypicalItemsWithoutTags() {
        FoodRem foodRem = new FoodRem();
        for (Item item : TypicalItems.getTypicalItemsWithoutTags()) {
            foodRem.addItem(item);
        }

        return foodRem;
    }
}
