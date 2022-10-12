package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches exactly the keywords given (case-insensitive).
 */
public class NameMatchesKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public NameMatchesKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        String personName = person.getName().fullName;
        return personName.equalsIgnoreCase(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameMatchesKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NameMatchesKeywordPredicate) other).keyword)); // state check
    }
}
