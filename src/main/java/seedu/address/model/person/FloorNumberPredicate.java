package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Floor Number} matches any of the floor numbers given.
 */
public class FloorNumberPredicate implements Predicate<Person> {
    private final List<Integer> floorNumbers;

    public FloorNumberPredicate(List<Integer> floorNumbers) {
        this.floorNumbers = floorNumbers;
    }

    @Override
    public boolean test(Person person) {
        return floorNumbers.stream()
                .anyMatch(floorNumber -> person.getFloorNumber().get().value.equals(floorNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FloorNumberPredicate // instanceof handles nulls
                && floorNumbers.equals(((FloorNumberPredicate) other).floorNumbers)); // state check
    }
}
