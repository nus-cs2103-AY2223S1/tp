package seedu.uninurse.model.remark;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.commons.util.StringUtil;
import seedu.uninurse.model.person.Patient;

/**
 * Tests that at least one of the Patient's Remark matches any of the keywords given.
 */
public class RemarkContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public RemarkContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient person) {
        return keywords.stream().anyMatch(keyword ->
                person.getRemarks().getInternalList().stream().anyMatch(
                        remark -> StringUtil.containsIgnoreCase(remark.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RemarkContainsKeywordsPredicate) other).keywords)); // state check
    }
}
