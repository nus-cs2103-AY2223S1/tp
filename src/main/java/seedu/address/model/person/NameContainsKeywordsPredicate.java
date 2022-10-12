package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches the keyword given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Creates a {@code NameContainsKeywordsPredicates} with keywords converted
     * to lower and duplicates removed.
     *
     * @param keywords Name to filter for.
     */
    public NameContainsKeywordsPredicate(String keyword) {
        requireNonNull(keyword);
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((NameContainsKeywordsPredicate) other).keyword)); // state check
    }

    @Override
    public int hashCode() {
        return keyword.hashCode();
    }

}
