package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class WardNumberContainsKeywordsPredicate implements Predicate<Person> {

    private final List<WardNumber> wardNumbers;

    public WardNumberContainsKeywordsPredicate(List<WardNumber> wardNumbers) {
        this.wardNumbers = wardNumbers;
    }

    @Override
    public boolean test(Person person) {
        return wardNumbers.stream()
                .anyMatch(wardNumber -> person.getWardNumber().get().equals(wardNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WardNumberContainsKeywordsPredicate // instanceof handles nulls
                && wardNumbers.equals(((WardNumberContainsKeywordsPredicate) other).wardNumbers)); // state check
    }
}
