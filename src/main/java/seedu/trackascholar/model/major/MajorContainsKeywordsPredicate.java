package seedu.trackascholar.model.major;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.trackascholar.commons.util.StringUtil;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Tests if any of the {@code Applicant}'s {@code Major} matches any of the keywords given.
 */
public class MajorContainsKeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    public MajorContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        Set<String[]> majors = applicant.getMajors()
                .stream()
                .map(major -> major.major.split("\\s+"))
                .collect(Collectors.toSet());

        ArrayList<String> partialMajorKeywords = new ArrayList<>();
        majors.forEach(x -> partialMajorKeywords.addAll(List.of(x)));

        return partialMajorKeywords.stream().anyMatch(partialKeyword -> {
            for (int i = 0; i < keywords.size(); i++) {
                if (StringUtil.containsWordIgnoreCase(partialKeyword, keywords.get(i))) {
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MajorContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MajorContainsKeywordsPredicate) other).keywords)); // state check
    }
}
