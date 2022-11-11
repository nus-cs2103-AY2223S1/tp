package seedu.pennywise.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.pennywise.testutil.ExpenditureBuilder;

public class DescriptionContainsKeywordsPredicateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DescriptionContainsKeywordsPredicate(null));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        List<String> keywords = List.of("delicious", "food");
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
                keywords
        );

        // Matching one keyword
        assertTrue(predicate.test(
                new ExpenditureBuilder().withDescription("delicious").build())
        );
        assertTrue(predicate.test(
                new ExpenditureBuilder().withDescription("food").build())
        );
        // Case-insensitivity
        assertTrue(predicate.test(
                new ExpenditureBuilder().withDescription("FooD").build())
        );
        // Matching all keywords
        assertTrue(predicate.test(
                new ExpenditureBuilder().withDescription("delicious food").build())
        );
        assertTrue(predicate.test(
                new ExpenditureBuilder().withDescription("food delicious").build())
        );
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        List<String> keywords = List.of("delicious", "food");
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
                keywords
        );

        // Non-matching keyword
        assertFalse(predicate.test(
                new ExpenditureBuilder().withDescription("pencil").build())
        );
    }

    @Test
    public void equals() {
        List<String> keywords = List.of("a", "b");
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
                keywords
        );

        // same object -> returns true
        assertEquals(predicate, predicate);

        // same values -> returns true
        DescriptionContainsKeywordsPredicate predicateCopy = new DescriptionContainsKeywordsPredicate(
                keywords
        );
        assertEquals(predicate, predicateCopy);

        // different types -> returns false
        assertNotEquals(predicate, keywords);

        // null -> returns false
        assertNotEquals(predicate, null);

        // different keywords -> returns false
        assertNotEquals(
                predicate,
                // Missing "b""
                new DescriptionContainsKeywordsPredicate(List.of("a"))
        );
        assertNotEquals(
                predicate,
                // Missing "a""
                new DescriptionContainsKeywordsPredicate(List.of("b"))
        );
        assertNotEquals(
                predicate,
                // Extra description
                new DescriptionContainsKeywordsPredicate(List.of(
                        "a", "b", "c")
                )
        );
    }
}
