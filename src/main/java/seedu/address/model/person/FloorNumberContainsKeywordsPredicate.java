package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Floor Number} matches any of the floor numbers given.
 */
public class FloorNumberContainsKeywordsPredicate implements Predicate<Person> {
    private final List<FloorNumber> floorNumbers;

    public FloorNumberContainsKeywordsPredicate(List<FloorNumber> floorNumbers) {
        this.floorNumbers = floorNumbers;
    }

    @Override
    public boolean test(Person person) {
        return floorNumbers.stream()
                .anyMatch(floorNumber -> person.getFloorNumber().get().equals(floorNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FloorNumberContainsKeywordsPredicate // instanceof handles nulls
                && floorNumbers.equals(((FloorNumberContainsKeywordsPredicate) other).floorNumbers)); // state check
    }
}
