package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests if a {@code Food} was recorded today.
 */
public class IsTodayPredicate implements Predicate<Food> {
    private final DateTime dateTime;

    /**
     * Constructs a {@code IsTodayPredicate}.
     */
    public IsTodayPredicate() {
        this.dateTime = new DateTime();
    }

    @Override
    public boolean test(Food food) {
        return dateTime.isOnSameDay(food.getDateTime());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsTodayPredicate // instanceof handles nulls
                && dateTime.equals(((IsTodayPredicate) other).dateTime)); // state check
    }
}
