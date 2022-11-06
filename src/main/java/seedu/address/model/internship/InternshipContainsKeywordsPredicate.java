package seedu.address.model.internship;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Tests that whether an {@code Internship}'s {@code Company Name}, {@code Internship Role},
 * {@code Internship Status}, and {@code Interview Date} match any of the keywords given.
 */
public class InternshipContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> companyNameKeywords;
    private final List<String> internshipRoleKeywords;
    private final List<String> internshipStatusKeywords;
    private final List<String> interviewDateKeywords;

    /**
     * Constructs a predicate for checking whether an {@code Internship}'s
     * {@code Company Name}, {@code Internship Role}, {@code Internship Status}, and {@code Interview Date}
     * match any of the keywords given.
     */
    public InternshipContainsKeywordsPredicate(
            List<String> companyNameKeywords,
            List<String> internshipRoleKeywords,
            List<String> internshipStatusKeywords,
            List<String> interviewDateKeywords) {
        this.companyNameKeywords = companyNameKeywords;
        this.internshipRoleKeywords = internshipRoleKeywords;
        this.internshipStatusKeywords = internshipStatusKeywords;
        this.interviewDateKeywords = interviewDateKeywords;
    }

    @Override
    public boolean test(Internship internship) {
        return (companyNameKeywords.isEmpty() || companyNameKeywords.stream().anyMatch(
                    keyword -> internship.getCompanyName().fullName.toLowerCase().contains(keyword.toLowerCase())))
                && (internshipRoleKeywords.isEmpty() || internshipRoleKeywords.stream().anyMatch(
                    keyword -> internship.getInternshipRole().roleName.toLowerCase().contains(keyword.toLowerCase())))
                && (internshipStatusKeywords.isEmpty() || internshipStatusKeywords.stream().anyMatch(
                    keyword -> internship.getInternshipStatus().toString().toLowerCase().contains(
                            keyword.toLowerCase())))
                && (interviewDateKeywords.isEmpty() || interviewDateKeywords.stream().anyMatch(
                    keyword -> internship.getInterviewDate() != null
                            && internship.getInterviewDate().toString().toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InternshipContainsKeywordsPredicate)) {
            return false;
        }

        InternshipContainsKeywordsPredicate otherPredicate = (InternshipContainsKeywordsPredicate) other;

        // solution adapted from
        // https://stackoverflow.com/a/36716166
        return Objects.equals(companyNameKeywords, otherPredicate.companyNameKeywords)
                && Objects.equals(internshipRoleKeywords, otherPredicate.internshipRoleKeywords)
                && Objects.equals(internshipStatusKeywords, otherPredicate.internshipStatusKeywords)
                && Objects.equals(interviewDateKeywords, otherPredicate.interviewDateKeywords);
    }

}
