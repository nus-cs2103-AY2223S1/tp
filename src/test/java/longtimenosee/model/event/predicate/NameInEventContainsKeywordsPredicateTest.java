package longtimenosee.model.event.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.EventBuilder;

public class NameInEventContainsKeywordsPredicateTest {
    @Test
    public void test_equals() {
        List<String> firstPredicateInput = List.of("Alice");
        List<String> secondPredicateInput = List.of("Peter");

        NameInEventContainsKeywordsPredicate firstPredicate =
                new NameInEventContainsKeywordsPredicate(firstPredicateInput);
        NameInEventContainsKeywordsPredicate secondPredicate =
                new NameInEventContainsKeywordsPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal keywords
        NameInEventContainsKeywordsPredicate firstPredicateCopy =
                new NameInEventContainsKeywordsPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal keywords
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsInput_returnsTrue() {
        // EP: single keyword
        NameInEventContainsKeywordsPredicate predicate = new NameInEventContainsKeywordsPredicate(List.of("Peter"));
        assertTrue(predicate.test(new EventBuilder().withName("Peter").build()));

        // EP: Name contains single keyword
        predicate = new NameInEventContainsKeywordsPredicate(List.of("Peter"));
        assertTrue(predicate.test(new EventBuilder().withName("Peter Lee").build()));

        // EP: Name contains multiple keywords
        predicate = new NameInEventContainsKeywordsPredicate(List.of("Bernard", "Lee"));
        assertTrue(predicate.test(new EventBuilder().withName("Bernard Lee Jin Yan").build()));

        // EP: Name contains at least one keyword
        predicate = new NameInEventContainsKeywordsPredicate(List.of("Peter", "Bernard"));
        assertTrue(predicate.test(new EventBuilder().withName("Bernard Lee").build()));

        // EP: Name contains keyword with different casing
        predicate = new NameInEventContainsKeywordsPredicate(List.of("BERnard"));
        assertTrue(predicate.test(new EventBuilder().withName("Bernard Lee").build()));
    }

    @Test
    public void test_nameDoesNotContainInput_returnsFalse() {
        // EP: name does not contain any keyword
        NameInEventContainsKeywordsPredicate predicate = new NameInEventContainsKeywordsPredicate(List.of("Alice"));
        assertFalse(predicate.test(new EventBuilder().withName("Peter Lee").build()));

        // EP: Name only partially contains input
        predicate = new NameInEventContainsKeywordsPredicate(List.of("eter"));
        assertFalse(predicate.test(new EventBuilder().withName("Peter Lee").build()));
    }
}
