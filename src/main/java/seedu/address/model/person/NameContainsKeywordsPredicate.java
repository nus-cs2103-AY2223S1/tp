package seedu.address.model.person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final Set<String> keywords;

    /**
     * Creates a {@code NameContainsKeywordsPredicates} with keywords converted
     * to lower and duplicates removed.
     *
     * @param keywords List of names to filter for.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = new HashSet<>();
        keywords.forEach((keyword) -> this.keywords.add(keyword.toLowerCase()));
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return keywords.stream().map(str -> str.hashCode()).reduce(0, (code1, code2) -> code1 + code2);
    }

}
