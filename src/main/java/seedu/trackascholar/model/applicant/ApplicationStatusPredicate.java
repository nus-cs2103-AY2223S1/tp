package seedu.trackascholar.model.applicant;

import java.util.function.Predicate;

import seedu.trackascholar.commons.util.StringUtil;


/**
 * Tests that a {@code Applicant}'s {@code Status} matches the keyword given.
 */
public class ApplicationStatusPredicate implements Predicate<Applicant> {
    private final String keyword;

    public ApplicationStatusPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Applicant applicant) {
        return StringUtil.containsWordIgnoreCase(applicant
                .getApplicationStatus().applicationStatus, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationStatusPredicate // instanceof handles nulls
                && keyword.equals(((ApplicationStatusPredicate) other).keyword)); // state check
    }


}
