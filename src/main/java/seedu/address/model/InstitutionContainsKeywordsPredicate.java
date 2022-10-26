package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.tutor.Tutor;

/**
 * Tests that a {@code Tutor}'s {@code Institution} matches any of the keywords given.
 */
public class InstitutionContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public InstitutionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Tutor) {
            Tutor tutor = (Tutor) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutor.getInstitution()
                            .institution, keyword));
        }
        throw new ClassCastException("InstitutionContainsKeyword predicate can only be applied to Person.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InstitutionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InstitutionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
