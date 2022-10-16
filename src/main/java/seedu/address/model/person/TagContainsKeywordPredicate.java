package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the keyword given.
 */
public class TagContainsKeywordPredicate implements Predicate<Patient> {

    public final String keyword;

    public TagContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getTags().stream()
                .anyMatch(tag -> tag.toString().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((TagContainsKeywordPredicate) other).keyword)); // state check
    }
}
