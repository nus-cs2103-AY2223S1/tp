package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code name} starts with the keywords given (case-insensitive).
 */
public class NameStartsWithKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public NameStartsWithKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        requireNonNull(person);

        String name = person.getName().fullName;

        assert name != null;

        return (name.toLowerCase()).startsWith(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameStartsWithKeywordPredicate // instanceof handles nulls
                && keyword.equals(((NameStartsWithKeywordPredicate) other).keyword)); // state check
    }
}
