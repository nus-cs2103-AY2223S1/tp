package coydir.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Position} matches any of the keywords given.
 */
public class PositionContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public PositionContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String lowerCasePosition = person.getPosition().value.toLowerCase();
        return lowerCasePosition.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((PositionContainsKeywordPredicate) other).keyword)); // state check
    }

}
