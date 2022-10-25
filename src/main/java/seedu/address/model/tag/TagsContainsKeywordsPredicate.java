package seedu.address.model.tag;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

public class TagsContainsKeywordsPredicate implements Predicate<Person> {

    private final String keyword;

    public TagsContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(keyword, tag.tagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((TagsContainsKeywordsPredicate) other).keyword)); // state check
    }
}
