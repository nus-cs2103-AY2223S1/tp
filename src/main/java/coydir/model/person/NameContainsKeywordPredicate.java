package coydir.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public NameContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String lowerCaseName = person.getName().fullName.toLowerCase();
        return lowerCaseName.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NameContainsKeywordPredicate) other).keyword)); // state check
    }

}
