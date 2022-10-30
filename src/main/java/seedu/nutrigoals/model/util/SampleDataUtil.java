package seedu.nutrigoals.model.util;

import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;

/**
 * Contains utility methods for populating {@code NutriGoals} with sample data.
 */
public class SampleDataUtil {
    public static Food[] getSampleFoods() {
        return new Food[] {
            new Food(new Name("cereal"), new Calorie("150"), getTag("breakfast"), new DateTime("2022-09-15T08:00")),
            new Food(new Name("ban mian"), new Calorie("500"), getTag("lunch"), new DateTime("2022-09-15T14:00")),
            new Food(new Name("laksa"), new Calorie("600"), getTag("dinner"), new DateTime("2022-09-15T18:00")),

            new Food(new Name("croissant"), new Calorie("300"), getTag("breakfast"), new DateTime("2022-10-23T09:00")),
            new Food(new Name("fried rice"), new Calorie("500"), getTag("lunch"), new DateTime("2022-10-23T12:00")),
            new Food(new Name("soya milk"), new Calorie("100"), getTag("lunch"), new DateTime("2022-10-23T13:00")),
            new Food(new Name("sushi"), new Calorie("400"), getTag("dinner"), new DateTime("2022-10-23T19:00")),

            new Food(new Name("bread"), new Calorie("70"), getTag("breakfast")),
            new Food(new Name("milo"), new Calorie("120"), getTag("breakfast")),
            new Food(new Name("porridge"), new Calorie("80"), getTag("lunch")),
            new Food(new Name("korean bbq"), new Calorie("1000"), getTag("dinner"))
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
