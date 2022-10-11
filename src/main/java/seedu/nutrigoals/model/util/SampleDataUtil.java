package seedu.nutrigoals.model.util;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;

/**
 * Contains utility methods for populating {@code NutriGoals} with sample data.
 */
public class SampleDataUtil {
    public static Food[] getSampleFoods() {
        return new Food[] {
            new Food(new Name("Bread"), new Calorie(), getTag("breakfast")),
            new Food(new Name("Croissant"), new Calorie(), getTag("breakfast")),
            new Food(new Name("Porridge"), new Calorie(), getTag("lunch")),
            new Food(new Name("KBBQ"), new Calorie(), getTag("dinner")),
            new Food(new Name("Sushi"), new Calorie(), getTag("dinner")),
            new Food(new Name("Milo"), new Calorie(), getTag("lunch"))
        };
    }

    public static ReadOnlyNutriGoals getSampleNutriGoals() {
        NutriGoals sampleAb = new NutriGoals();
        for (Food sampleFood : getSampleFoods()) {
            sampleAb.addFood(sampleFood);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Tag getTag(String string) {
        return new Tag(string);
    }

}
