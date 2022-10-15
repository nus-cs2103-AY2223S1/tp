package soconnect.model.person.SearchPerson;

import soconnect.commons.util.StringUtil;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.Prefix;
import soconnect.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

import static soconnect.model.person.SearchPerson.SearchPrefix.SearchPrefixCommand;
import static soconnect.model.person.SearchPerson.SearchPrefix.convertPrefixToEnumType;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactMightBeRelevantPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private boolean isNameContained = false;
    private boolean isAddressContained = false;
    private boolean isEmailContained = false;
    private boolean isPhoneContained = false;
    private boolean isTagContained = false;

    /**
     * Constructs the {@code ContactMightBeRelevantPredicate} object.
     */
    public ContactMightBeRelevantPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    private String getHalfKeyword(String keyword) {
        return keyword.substring(0, keyword.length() / 2);
    }

    @Override
    public boolean test(Person person) {
        isNameContained = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(
                        person.getName().fullName, getHalfKeyword(keyword)));
        isAddressContained = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(
                        person.getAddress().value, getHalfKeyword(keyword)));
        isEmailContained = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(
                        person.getEmail().value, getHalfKeyword(keyword)));
        isPhoneContained = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsKeywordsIgnoreCase(
                        person.getPhone().value, getHalfKeyword(keyword)));
        isTagContained = keywords.stream()
                .anyMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsKeywordsIgnoreCase(tag.tagName, getHalfKeyword(keyword))));
        return isNameContained || isAddressContained || isEmailContained || isPhoneContained || isTagContained;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactMightBeRelevantPredicate // instanceof handles nulls
                // state check
                && keywords.equals(((ContactMightBeRelevantPredicate) other).keywords));
    }

}
