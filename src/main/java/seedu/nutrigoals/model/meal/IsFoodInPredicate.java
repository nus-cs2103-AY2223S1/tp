package seedu.nutrigoals.model.meal;

import java.util.function.Predicate;

/**
 * Tests if a {@code Food} is inside the list.
 */
public class IsFoodInPredicate implements Predicate<Food> {
    private final Name foodName;

    /**
     * Constructor Method for the class
     * @param foodName checks if there is a food with foodName in the list
     */
    public IsFoodInPredicate(Name foodName) {
        this.foodName = foodName;
    }

    /**
     * returns the foodName of the predicate
     * @return foodName of the predicate
     */
    public Name getFoodName() {
        return foodName;
    }

    @Override
    public boolean test(Food food) {
        return food.getName().equals(foodName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFoodInPredicate // instanceof handles nulls
                && getFoodName().equals(((IsFoodInPredicate) other).getFoodName())); // state check
    }
}
