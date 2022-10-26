package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code module code} matches any of the keywords given.
 */
public class PersonDetailsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonDetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String personDetails = person.getName().toString() + " " + person.getModuleCode().toString() + " "
                + person.getEmail().toString() + " " + person.getPhone().toString() + " " + person.getTags().toString();
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(personDetails, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonDetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonDetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
