package seedu.uninurse.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.commons.util.StringUtil;
import seedu.uninurse.model.person.Person;

/**
 * Tests that at least one of the Person's Tag matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                person.getTags().getInternalList().stream().anyMatch(
                    tag -> StringUtil.containsIgnoreCase(tag.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
