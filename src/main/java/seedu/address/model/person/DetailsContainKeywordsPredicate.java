package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class DetailsContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public DetailsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        System.out.println(person.getDetailsAsString());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsSequenceIgnoreCase(person.getDetailsAsString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainKeywordsPredicate) other).keywords)); // state check
    }

}
