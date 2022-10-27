package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code JobTitle} matches any of the keywords given.
 */
public class JobTitleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public JobTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getJob().getTitle().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobTitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
