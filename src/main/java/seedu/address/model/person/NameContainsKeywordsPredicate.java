package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends FindPredicate {
    private final List<String> keywords;

    /**
     * Constructs a {@code NameContainsKeywordsPredicate}.
     *
     * @param keywords keywords to be tested against.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
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

}
