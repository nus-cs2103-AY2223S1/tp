package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intrack.testutil.InternshipBuilder;

public class TagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagsContainKeywordsPredicate firstPredicate =
                new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        TagsContainKeywordsPredicate secondPredicate =
                new TagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainKeywordsPredicate firstPredicateCopy =
                new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different internship -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // One keyword
        TagsContainKeywordsPredicate predicate =
                new TagsContainKeywordsPredicate(Collections.singletonList("Remote"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Urgent", "Remote").build()));

        // Multiple keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("Remote", "Urgent"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Urgent", "Remote").build()));

        // Only one matching keyword
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("Urgent", "Remote"));
        assertTrue(predicate.test(new InternshipBuilder().withTags("Urgent").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new InternshipBuilder().withTags("Urgent").build()));

        // Non-matching keyword
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("Urgent"));
        assertFalse(predicate.test(new InternshipBuilder().withTags("Remote").build()));

        // Keywords match salary, email and position, but does not match tags
        predicate = new TagsContainKeywordsPredicate(
                Arrays.asList("careers@airbnb.com", "Software", "Engineer", "12345"));
        assertFalse(predicate.test(new InternshipBuilder().withPosition("Software Engineer").withSalary("12345")
                .withEmail("careers@airbnb.com").build()));

        // Mixed-case keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("uRgEnT", "rEmOtE"));
        assertFalse(predicate.test(new InternshipBuilder().withTags("Urgent", "Remote").build()));
    }
}
