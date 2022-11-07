package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that an {@code Internship}'s {@code ApplicationStatus} matches the filter given.
 */
public class InternshipHasApplicationStatusPredicate implements Predicate<Internship> {
    private final ApplicationStatus applicationStatus;

    /**
     * Constructs an {@code InternshipHasApplicationStatusPredicate}.
     *
     * @param applicationStatus The {@code ApplicationStatus} to use as filter.
     */
    public InternshipHasApplicationStatusPredicate(ApplicationStatus applicationStatus) {
        requireNonNull(applicationStatus);
        this.applicationStatus = applicationStatus;
    }

    @Override
    public boolean test(Internship internship) {
        return applicationStatus.equals(internship.getApplicationStatus());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipHasApplicationStatusPredicate // instanceof handles nulls
                && applicationStatus.equals(((InternshipHasApplicationStatusPredicate) other)
                    .applicationStatus)); // state check
    }
}
