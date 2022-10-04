package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_NAME;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_PHONE;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_TAG;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactContainsAllKeywordsPredicate implements Predicate<Person> {
    private final List<List<String>> searchedKeywords;
    private final List<String> prefixes;
    private boolean isNameContained = true;
    private boolean isAddressContained = true;
    private boolean isEmailContained = true;
    private boolean isPhoneContained = true;
    private boolean isTagContained = true;

    public ContactContainsAllKeywordsPredicate(List<String> prefixes, List<List<String>> searchedKeywords) {
        this.searchedKeywords = searchedKeywords;
        this.prefixes = prefixes;
    }

    @Override
    public boolean test(Person person) {
        for (int i = 0; i < prefixes.size(); i++) {
            String prefix = prefixes.get(i);
            List<String> keywords = searchedKeywords.get(i);
            if (Objects.equals(prefix, INDICATOR_NAME)) {
                isNameContained = isNameContained && keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
            } else if (Objects.equals(prefix, INDICATOR_ADDRESS)) {
                isAddressContained = isAddressContained && keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword));
            } else if (Objects.equals(prefix, INDICATOR_EMAIL)) {
                isEmailContained = isEmailContained && keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword));
            } else if (Objects.equals(prefix, INDICATOR_PHONE)) {
                isPhoneContained = isPhoneContained && keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
            } else if (Objects.equals(prefix, INDICATOR_TAG)) {
                isTagContained = true; // Implementation postponed, waiting for tag feature
            }
        }
        return isNameContained && isAddressContained && isEmailContained && isPhoneContained && isTagContained;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsAllKeywordsPredicate // instanceof handles nulls
                && searchedKeywords.equals(((ContactContainsAllKeywordsPredicate) other).searchedKeywords)); // state check
    }

}
