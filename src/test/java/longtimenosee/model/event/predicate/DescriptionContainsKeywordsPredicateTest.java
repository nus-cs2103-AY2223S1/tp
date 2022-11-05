package longtimenosee.model.event.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.EventBuilder;

public class DescriptionContainsKeywordsPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "meeting";
        String secondPredicateInput = "lunch break";

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateInput);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal description
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal description
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionContainsInput_returnsTrue() {
        // EP: single spacing
        // Note: Spacings wil be matched as if it is a character
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(" ");
        assertTrue(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));

        // EP: description contains whole keyword
        predicate = new DescriptionContainsKeywordsPredicate("meeting");
        assertTrue(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));

        // EP: description contains whole input with multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate("meeting with");
        assertTrue(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));

        predicate = new DescriptionContainsKeywordsPredicate("with Alice");
        assertTrue(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));

        // EP: description contains input with different casing
        predicate = new DescriptionContainsKeywordsPredicate("meeting WITH");
        assertTrue(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));
    }

    @Test
    public void test_descriptionDoesNotContainInput_returnsFalse() {
        // EP: description does not contain input
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate("lunch");
        assertFalse(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));

        // EP: description only contains part of input
        predicate = new DescriptionContainsKeywordsPredicate("lunch with Alice");
        assertFalse(predicate.test(new EventBuilder().withDescription("meeting with Alice").build()));
    }
}
