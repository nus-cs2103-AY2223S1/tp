package seedu.address.model.person;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate extends FindPredicate {
    private final List<String> phones;

    /**
     * Constructs a {@code PhoneContainsKeywordsPredicate}.
     *
     * @param phones Phones to be tested against.
     */
    public PhoneContainsKeywordsPredicate(List<String> phones) {
        super(phones);
        this.phones = phones.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        return phones.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && phones.equals(((PhoneContainsKeywordsPredicate) other).phones)); // state check
    }

}
