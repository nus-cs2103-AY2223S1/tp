package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_NAME;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_PHONE;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_TAG;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactContainsAnyKeywordsPredicate implements Predicate<Person> {
    private final List<List<String>> searchedKeywords;
    private final List<String> prefixes;
    private boolean isNameContained = false;
    private boolean isAddressContained = false;
    private boolean isEmailContained = false;
    private boolean isPhoneContained = false;
    private boolean isTagContained = false;

    /**
     * Constructs the ContactContainsAnyKeywordsPredicate object.
     */
    public ContactContainsAnyKeywordsPredicate(List<String> prefixes, List<List<String>> searchedKeywords) {
        this.searchedKeywords = searchedKeywords;
        this.prefixes = prefixes;
    }

    @Override
    public boolean test(Person person) {
        for (int i = 0; i < prefixes.size(); i++) {
            String prefix = prefixes.get(i);
            List<String> keywords = searchedKeywords.get(i);
            if (Objects.equals(prefix, INDICATOR_NAME)) {
                isNameContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            } else if (Objects.equals(prefix, INDICATOR_ADDRESS)) {
                isAddressContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
            } else if (Objects.equals(prefix, INDICATOR_EMAIL)) {
                isEmailContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
            } else if (Objects.equals(prefix, INDICATOR_PHONE)) {
                isPhoneContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
            } else if (Objects.equals(prefix, INDICATOR_TAG)) {
                isTagContained = true; // Implementation postponed, waiting for tag feature
            }
        }
        return isNameContained || isAddressContained || isEmailContained || isPhoneContained || isTagContained;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsAnyKeywordsPredicate // instanceof handles nulls
                // state check
                && searchedKeywords.equals(((ContactContainsAnyKeywordsPredicate) other).searchedKeywords));
    }

}
