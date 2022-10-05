package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_NAME;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_PHONE;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String prefix;

    /**
     * Constructs the ContactContainsKeywordsPredicate object.
     */
    public ContactContainsKeywordsPredicate(String prefix, List<String> keywords) {
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(Person person) {
        switch(prefix) {
        case INDICATOR_NAME:
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        case INDICATOR_ADDRESS:
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
        case INDICATOR_EMAIL:
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
        case INDICATOR_PHONE:
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
        case INDICATOR_TAG:
            return false; // Implementation postponed, waiting for tag feature
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContactContainsKeywordsPredicate) other).keywords)); // state check
    }

}
