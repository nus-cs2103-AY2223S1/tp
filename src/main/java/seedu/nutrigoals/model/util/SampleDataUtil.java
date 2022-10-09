package seedu.nutrigoals.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
            new Food(new Name("Bread"), new Calorie(), getTagSet("breakfast")),
            new Food(new Name("Croissant"), new Calorie(), getTagSet("breakfast")),
            new Food(new Name("Porridge"), new Calorie(), getTagSet("lunch")),
            new Food(new Name("KBBQ"), new Calorie(), getTagSet("dinner")),
            new Food(new Name("Sushi"), new Calorie(), getTagSet("dinner")),
            new Food(new Name("Milo"), new Calorie(), getTagSet("lunch"))
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
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
