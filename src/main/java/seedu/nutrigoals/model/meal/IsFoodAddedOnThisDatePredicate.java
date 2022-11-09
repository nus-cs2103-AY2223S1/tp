package seedu.nutrigoals.model.meal;

import java.util.function.Predicate;

/**
 * Tests if a {@code Food} was added on a specific date.
 */
public class IsFoodAddedOnThisDatePredicate implements Predicate<Food> {
    private final DateTime dateTime;

    /**
     * Constructs a {@code IsFoodAddedOnThisDatePredicate}.
     */
    public IsFoodAddedOnThisDatePredicate(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns the date that the predicate is testing for.
     * @return A string representing the date that the predicate is testing for.
     */
    public String getDate() {
        return dateTime.getDate();
    }

    @Override
    public boolean test(Food food) {
        return dateTime.isOnSameDay(food.getDateTime());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFoodAddedOnThisDatePredicate // instanceof handles nulls
                && getDate().equals(((IsFoodAddedOnThisDatePredicate) other).getDate())); // state check
    }
}
