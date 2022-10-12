package paymelah.model.person;

import static paymelah.commons.util.StringUtil.containsWordIgnoreCase;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code DebtList} contains a debt that matches
 * any of the keywords given.
 */
public class DebtContainsKeywordsPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS = "Keyword should not be blank";

    private final List<String> keywords;

    public DebtContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return person.getDebts().asList().stream().anyMatch(debt -> keywords.stream()
                .anyMatch(keyword -> containsWordIgnoreCase(debt.getDescription().toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DebtContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((DebtContainsKeywordsPredicate) other).keywords)); // state check
    }

}
