package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} and/or {@code Phone} and/or {@code Email} and/or {@code Address}
 * matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Name> nameKeywords;
    private final List<Phone> phoneKeywords;
    private final List<Email> emailKeywords;
    private final List<Address> addressKeywords;
    private final Set<Tag> tags;

    /**
     * Constructs an {@code PersonContainsKeywordsPredicate}.
     *
     * @param nameKeywords A list containing keywords for {@code Name}.
     * @param phoneKeywords A list containing keywords for {@code Phone}.
     * @param emailKeywords A list containing keywords for {@code Email}.
     * @param addressKeywords A list containing keywords for {@code Address}.
     */
    public PersonContainsKeywordsPredicate(List<Name> nameKeywords,
            List<Phone> phoneKeywords, List<Email> emailKeywords, List<Address> addressKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneKeywords = phoneKeywords;
        this.emailKeywords = emailKeywords;
        this.addressKeywords = addressKeywords;
        this.tags = new HashSet<>();

    }

    /**
     * Constructs an {@code PersonContainsKeywordsPredicate}.
     *
     * @param tags A set containing search terms for {@code Tag}.
     */
    public PersonContainsKeywordsPredicate(Set<Tag> tags) {
        this.nameKeywords = new ArrayList<>();
        this.phoneKeywords = new ArrayList<>();
        this.emailKeywords = new ArrayList<>();
        this.addressKeywords = new ArrayList<>();
        this.tags = tags;
    }

    // TODO: Implement for tags
    @Override
    public boolean test(Person person) {
        return (nameKeywords.isEmpty() || nameKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword.toString())))
                && (phoneKeywords.isEmpty() || phoneKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword.toString())))
                && (emailKeywords.isEmpty() || emailKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword.toString())))
                && (addressKeywords.isEmpty() || addressKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword.toString())))
                && (tags.isEmpty() || !Collections.disjoint(tags, person.getTags()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && nameKeywords.equals(((PersonContainsKeywordsPredicate) other).nameKeywords)) // state check
                && emailKeywords.equals(((PersonContainsKeywordsPredicate) other).emailKeywords)
                && addressKeywords.equals(((PersonContainsKeywordsPredicate) other).addressKeywords)
                && tags.equals(((PersonContainsKeywordsPredicate) other).tags);
    }
}
