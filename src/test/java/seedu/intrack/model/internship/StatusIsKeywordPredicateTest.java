package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.intrack.testutil.InternshipBuilder;

public class StatusIsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "Offered";
        String secondPredicateKeyword = "Rejected";

        StatusIsKeywordPredicate firstPredicate = new StatusIsKeywordPredicate(firstPredicateKeyword);
        StatusIsKeywordPredicate secondPredicate = new StatusIsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusIsKeywordPredicate firstPredicateCopy = new StatusIsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different internship -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_statusIsKeyword_returnsTrue() {
        // Rejected keyword
        StatusIsKeywordPredicate predicate = new StatusIsKeywordPredicate("Rejected");
        assertTrue(predicate.test(new InternshipBuilder().withStatus("Rejected").build()));

        // Offered keyword
        predicate = new StatusIsKeywordPredicate("Offered");
        assertTrue(predicate.test(new InternshipBuilder().withStatus("Offered").build()));

        // Progress keyword
        predicate = new StatusIsKeywordPredicate("Progress");
        assertTrue(predicate.test(new InternshipBuilder().withStatus("Progress").build()));

        // Mixed-case keyword
        predicate = new StatusIsKeywordPredicate("oFFeReD");
        assertTrue(predicate.test(new InternshipBuilder().withStatus("Offered").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusIsKeywordPredicate predicate = new StatusIsKeywordPredicate("");
        assertFalse(predicate.test(new InternshipBuilder().withStatus("Offered").build()));

        // Non-matching keyword
        predicate = new StatusIsKeywordPredicate("Offered");
        assertFalse(predicate.test(new InternshipBuilder().withStatus("Rejected").build()));

        // Keyword matches name and email, but does not match status
        predicate = new StatusIsKeywordPredicate("Progress");
        assertFalse(predicate.test(new InternshipBuilder().withName("Progress").withEmail("progress@email.com")
                .withStatus("Offered").build()));
    }
}
