package seedu.intrack.model.internship;

import java.util.function.Predicate;

/**
 * Tests that a {@code Internship}'s {@code Status} matches the keyword given.
 */
public class StatusIsKeywordPredicate implements Predicate<Internship> {
    private final String keyword;

    public StatusIsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Internship internship) {
        return keyword.equalsIgnoreCase(internship.getStatus().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusIsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((StatusIsKeywordPredicate) other).keyword)); // state check
    }

}
