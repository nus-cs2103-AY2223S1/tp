package soconnect.model.person.search;

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
public class ContactMightBeRelevantPredicate implements Predicate<Person> {
    private final ArgumentMultimap argMultimap;
    private boolean isNameContained = false;
    private boolean isAddressContained = false;
    private boolean isEmailContained = false;
    private boolean isPhoneContained = false;
    private boolean isTagContained = false;
    private boolean isSearchAccuracyReduced = false;

    /**
     * Constructs the {@code ContactMightBeRelevantPredicate} object.
     * Tests the person by matching the keywords to all the information field, ignoring prefix groupings.
     */
    public ContactMightBeRelevantPredicate(ArgumentMultimap argMultimap, boolean isSearchAccuracyReduced) {
        this.argMultimap = argMultimap;
        this.isSearchAccuracyReduced = isSearchAccuracyReduced;
    }

    @Override
    public boolean test(Person person) {
        for (Prefix prefix : argMultimap.getAllPrefixes()) {
            SearchPrefix.SearchPrefixCommand prefixCommand = convertPrefixToEnumType(prefix);
            List<String> keywords = argMultimap.getAllValues(prefix);
            switch (prefixCommand) {
            case NAME:
                isNameContained = keywordsContainName(keywords, person);
                break;
            case ADDRESS:
                isAddressContained = keywordsContainAddress(keywords, person);
                break;
            case EMAIL:
                isEmailContained = keywordsContainEmail(keywords, person);
                break;
            case PHONE:
                isPhoneContained = keywordsContainPhone(keywords, person);
                break;
            case TAG:
                isTagContained = keywordsContainTag(keywords, person);
                break;
            default:
                break;
            }
        }
        return isNameContained || isAddressContained || isEmailContained || isPhoneContained || isTagContained;
    }

    private boolean keywordsContainName(List<String> keywords, Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsSomeKeywordsIgnoreCase(person.getName().fullName, keyword, isSearchAccuracyReduced));
    }

    private boolean keywordsContainAddress(List<String> keywords, Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsSomeKeywordsIgnoreCase(person.getAddress().value, keyword, isSearchAccuracyReduced));
    }

    private boolean keywordsContainEmail(List<String> keywords, Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsSomeKeywordsIgnoreCase(person.getEmail().value, keyword, isSearchAccuracyReduced));
    }

    private boolean keywordsContainPhone(List<String> keywords, Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsSomeKeywordsIgnoreCase(person.getPhone().value, keyword, isSearchAccuracyReduced));
    }

    private boolean keywordsContainTag(List<String> keywords, Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil
                                .containsSomeKeywordsIgnoreCase(tag.tagName, keyword, isSearchAccuracyReduced)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactMightBeRelevantPredicate // instanceof handles nulls
                // state check
                && argMultimap.equals(((ContactMightBeRelevantPredicate) other).argMultimap));
    }

}
