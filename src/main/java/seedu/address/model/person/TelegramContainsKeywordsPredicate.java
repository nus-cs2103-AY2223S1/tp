package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code telegram} matches any of the keywords given.
 */
public class TelegramContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public TelegramContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getTelegram().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TelegramContainsKeywordsPredicate) other).keywords)); // state check
    }
}
