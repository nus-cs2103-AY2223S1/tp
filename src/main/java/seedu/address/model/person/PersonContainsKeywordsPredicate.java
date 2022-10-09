package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} and/or {@code Phone} and/or {@code Email} and/or {@code Address}
 * matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Name> nameKeywords;
    private final List<Phone> phoneKeywords;
    private final List<Email> emailKeywords;
    private final List<Address> addressKeywords;

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
    }

    @Override
    public boolean test(Person person) {
        return (nameKeywords.isEmpty() || nameKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword.toString())))
                && (phoneKeywords.isEmpty() || phoneKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword.toString())))
                && (emailKeywords.isEmpty() || emailKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getEmail().value, keyword.toString())))
                && (addressKeywords.isEmpty() || addressKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword.toString())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        PersonContainsKeywordsPredicate castedOther = (PersonContainsKeywordsPredicate) other;

        return nameKeywords.equals(castedOther.nameKeywords)
                && phoneKeywords.equals(castedOther.phoneKeywords)
                && emailKeywords.equals(castedOther.emailKeywords)
                && addressKeywords.equals(castedOther.addressKeywords);
    }
}
