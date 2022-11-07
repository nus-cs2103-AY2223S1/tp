package seedu.address.model.tag;

import java.util.function.Predicate;

import seedu.address.model.person.Person;


/**
 * Tests that any of a {@code Person}'s {@code Tags} match any of the keywords given.
 */
public class TagContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public TagContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(tag -> tag.tagName.equalsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((TagContainsKeywordPredicate) other).keyword)); // state check
    }

}
