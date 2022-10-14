package seedu.phu.model.internship;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Internship}'s {@code Name} matches any of the keywords given.
 */
public class ExactMatchPredicate implements Predicate<Internship> {
    private final List<Internship> internships;

    public ExactMatchPredicate(List<Internship> internships) {
        this.internships = internships;
    }

    @Override
    public boolean test(Internship internshipY) {
        return internships.stream()
                .anyMatch(internshipX -> internshipX == internshipY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExactMatchPredicate // instanceof handles nulls
                && internships.equals(((ExactMatchPredicate) other).internships)); // state check
    }

}
