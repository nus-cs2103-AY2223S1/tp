package soconnect.model.person.search;

import java.util.List;
import java.util.function.Predicate;

import soconnect.commons.util.StringUtil;
import soconnect.model.person.Person;

/**
 * Tests that a {@code Person}'s information matches the keyword given.
 */
public class ContactMightBeRelevantPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs the {@code ContactMightBeRelevantPredicate} object.
     * Tests the person by matching the keywords to all the information field, ignoring prefix groupings.
     */
    public ContactMightBeRelevantPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean isNameContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getName().fullName, keyword
                ));
        boolean isAddressContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getAddress().value, keyword));
        boolean isEmailContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getEmail().value, keyword));
        boolean isPhoneContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getPhone().value, keyword));
        boolean isTagContained = keywords.stream()
                .allMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsSomeKeywordsIgnoreCase(tag.tagName, keyword)));
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
