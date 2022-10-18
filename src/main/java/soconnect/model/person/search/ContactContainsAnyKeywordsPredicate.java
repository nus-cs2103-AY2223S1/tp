package soconnect.model.person.search;

import static soconnect.model.person.search.SearchPrefix.SearchPrefixCommand;
import static soconnect.model.person.search.SearchPrefix.convertPrefixToEnumType;

import java.util.List;
import java.util.function.Predicate;

import soconnect.commons.util.StringUtil;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.Prefix;
import soconnect.model.person.Person;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactContainsAnyKeywordsPredicate implements Predicate<Person> {
    private final ArgumentMultimap argMultimap;
    private boolean isNameContained = false;
    private boolean isAddressContained = false;
    private boolean isEmailContained = false;
    private boolean isPhoneContained = false;
    private boolean isTagContained = false;

    /**
     * Constructs the {@code ContactContainsAnyKeywordsPredicate} object.
     */
    public ContactContainsAnyKeywordsPredicate(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    @Override
    public boolean test(Person person) {
        for (Prefix prefix : argMultimap.getAllPrefixes()) {
            SearchPrefixCommand prefixCommand = convertPrefixToEnumType(prefix);
            List<String> keywords = argMultimap.getAllValues(prefix);
            switch (prefixCommand) {
            case NAME:
                isNameContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getName().fullName, keyword));
                break;
            case ADDRESS:
                isAddressContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getAddress().value, keyword));
                break;
            case EMAIL:
                isEmailContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getEmail().value, keyword));
                break;
            case PHONE:
                isPhoneContained = keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getPhone().value, keyword));
                break;
            case TAG:
                isTagContained = keywords.stream()
                        .anyMatch(keyword -> person.getTags().stream()
                                .anyMatch(tag -> StringUtil.containsKeywordsIgnoreCase(tag.tagName, keyword)));
                break;
            default:
                break;
            }
        }
        return isNameContained || isAddressContained || isEmailContained || isPhoneContained || isTagContained;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsAnyKeywordsPredicate // instanceof handles nulls
                // state check
                && argMultimap.equals(((ContactContainsAnyKeywordsPredicate) other).argMultimap));
    }

}
