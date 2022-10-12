package seedu.foodrem.model.util;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemUnit;

/**
 * Contains utility methods for populating {@code FoodRem} with sample data.
 */
public class SampleDataUtil {
    public static Item[] getSampleItems() {
        return new Item[]{
            new Item(new ItemName("Potatoes"),
                    new ItemQuantity("10"),
                    new ItemUnit("kg"),
                    new ItemBoughtDate("11-11-2022"),
                    new ItemExpiryDate("11-11-2022")),
            new Item(new ItemName("Cucumbers"),
                    new ItemQuantity("2000"),
                    new ItemUnit("grams"),
                    new ItemBoughtDate("12-12-2022"),
                    new ItemExpiryDate("12-12-2022"))
        };
    }

    public static ReadOnlyFoodRem getSampleFoodRem() {
        FoodRem sampleFoodRem = new FoodRem();
        for (Item sampleItem : getSampleItems()) {
            sampleFoodRem.addItem(sampleItem);
        }
        return sampleFoodRem;
    }

    // TODO: Implement with tags
    ///**
    // * Returns a tag set containing the list of strings given.
    // */
    //public static Set<Tag> getTagSet(String... strings) {
    //    return Arrays.stream(strings)
    //            .map(Tag::new)
    //            .collect(Collectors.toSet());
    //}

}
