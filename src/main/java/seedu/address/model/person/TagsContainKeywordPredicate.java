package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagsContainKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public TagsContainKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(tag -> StringUtil.containsWordIgnoreCase(keyword, tag.getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordPredicate // instanceof handles nulls
                && keyword.equals(((TagsContainKeywordPredicate) other).keyword)); // state check
    }

}
