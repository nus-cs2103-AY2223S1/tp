package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests if a {@code Food} was added today.
 */
public class IsFoodAddedTodayPredicate implements Predicate<Food> {
    private final DateTime dateTime;

    /**
     * Constructs a {@code IsFoodAddedTodayPredicate}.
     */
    public IsFoodAddedTodayPredicate() {
        this.dateTime = new DateTime();
    }

    @Override
    public boolean test(Food food) {
        return dateTime.isOnSameDay(food.getDateTime());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFoodAddedTodayPredicate // instanceof handles nulls
                && dateTime.equals(((IsFoodAddedTodayPredicate) other).dateTime)); // state check
    }
}
