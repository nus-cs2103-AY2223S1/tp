package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class PersonTagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonTagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean result = false;

        Set<Tag> tagSet = person.getTags();
        for (Tag tag : tagSet) {
            result = result || keywords.stream().anyMatch(
                    keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword));
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonTagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
