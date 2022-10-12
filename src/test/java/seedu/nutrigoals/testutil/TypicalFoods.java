package seedu.nutrigoals.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.meal.Food;

/**
 * A utility class containing a list of {@code Food} objects to be used in tests.
 */
public class TypicalFoods {

    public static final Food APPLE = new FoodBuilder().withName("Apple")
            .withTag("breakfast").build();
    public static final Food BREAD = new FoodBuilder().withName("Bread")
            .withTag("breakfast").build();
    public static final Food CARBONARA = new FoodBuilder().withName("Carbonara")
            .withTag("lunch").build();
    public static final Food DRAGON_FRUIT = new FoodBuilder().withName("Dragon fruit")
            .withTag("lunch").build();
    public static final Food EGG_SOUP = new FoodBuilder().withName("Egg soup")
            .withTag("dinner").build();
    public static final Food FISHCAKE = new FoodBuilder().withName("Fishcake")
            .withTag("dinner").build();
    public static final Food GRAPES = new FoodBuilder().withName("Grapes")
            .withTag("dinner").build();

    // Manually added
    public static final Food SUSHI = new FoodBuilder().withName("Sushi")
            .withTag("dinner").build();
    public static final Food PANCAKE = new FoodBuilder().withName("Pancake")
            .withTag("lunch").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFoods() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical foods.
     */
    public static NutriGoals getTypicalNutriGoals() {
        NutriGoals ab = new NutriGoals();
        for (Food food : getTypicalFoods()) {
            ab.addFood(food);
        }
        return ab;
    }

    public static List<Food> getTypicalFoods() {
        return new ArrayList<>(Arrays.asList(APPLE, BREAD, CARBONARA, DRAGON_FRUIT, EGG_SOUP, FISHCAKE, GRAPES));
    }
}
