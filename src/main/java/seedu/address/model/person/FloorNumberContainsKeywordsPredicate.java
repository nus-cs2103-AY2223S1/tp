package seedu.address.model.person;

import java.util.function.Predicate;
import java.util.List;

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
