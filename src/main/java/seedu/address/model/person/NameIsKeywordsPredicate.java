package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches all the keywords given.
 */
public class NameIsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameIsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.toString().equalsIgnoreCase(person.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NameIsKeywordsPredicate
                && keywords.equals(((NameIsKeywordsPredicate) other).keywords));
    }
}
