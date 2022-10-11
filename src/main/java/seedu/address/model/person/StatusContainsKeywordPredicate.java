package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Applicant}'s {@code Status} matches the keyword given.
 */
public class StatusContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public StatusContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsWordIgnoreCase(person.getApplicationStatus().applicationStatus, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((StatusContainsKeywordPredicate) other).keyword)); // state check
    }


}
