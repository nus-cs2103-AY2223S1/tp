package seedu.intrack.model.internship;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that the keyword is present in {@code Internship}'s {@code Tags}.
 */
public class TagsContainKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public TagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> internship.getTags().stream()
                .anyMatch(tag -> tag.tagName.equals(keyword)));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainKeywordsPredicate) other).keywords)); // state check
    }

}
