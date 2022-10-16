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
     * Tests the person by matching the keywords to all the information field, ignoring prefix groupings.
     */
    public ContactMightBeRelevantPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        isNameContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getName().fullName, keyword
                ));
        isAddressContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getAddress().value, keyword));
        isEmailContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getEmail().value, keyword));
        isPhoneContained = keywords.stream()
                .allMatch(keyword -> StringUtil.containsSomeKeywordsIgnoreCase(
                        person.getPhone().value, keyword));
        isTagContained = keywords.stream()
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
