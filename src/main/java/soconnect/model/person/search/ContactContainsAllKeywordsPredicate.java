package soconnect.model.person.search;

import soconnect.commons.util.StringUtil;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.Prefix;
import soconnect.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

import static soconnect.model.person.search.SearchPrefix.SearchPrefixCommand;
import static soconnect.model.person.search.SearchPrefix.convertPrefixToEnumType;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactContainsAllKeywordsPredicate implements Predicate<Person> {
    private final ArgumentMultimap argMultimap;
    private boolean isNameContained = true;
    private boolean isAddressContained = true;
    private boolean isEmailContained = true;
    private boolean isPhoneContained = true;
    private boolean isTagContained = true;

    /**
     * Constructs the {@code ContactContainsAllKeywordsPredicate} object.
     */
    public ContactContainsAllKeywordsPredicate(ArgumentMultimap argMultimap) {
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
                        .allMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getName().fullName, keyword));
                break;
            case ADDRESS:
                isAddressContained = keywords.stream()
                        .allMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getAddress().value, keyword));
                break;
            case EMAIL:
                isEmailContained = keywords.stream()
                        .allMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getEmail().value, keyword));
                break;
            case PHONE:
                isPhoneContained = keywords.stream()
                        .allMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(person.getPhone().value, keyword));
                break;
            case TAG:
                isTagContained = keywords.stream()
                        .allMatch(keyword -> person.getTags().stream()
                                .anyMatch(tag -> StringUtil.containsKeywordsIgnoreCase(tag.tagName, keyword)));
                break;
            default:
                break;
            }
        }
        return isNameContained && isAddressContained && isEmailContained && isPhoneContained && isTagContained;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsAllKeywordsPredicate // instanceof handles nulls
                // state check
                && argMultimap.equals(((ContactContainsAllKeywordsPredicate) other).argMultimap));
    }
}
