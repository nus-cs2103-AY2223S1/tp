package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StaffBuilder;
public class StaffNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        final String firstPredicateKeyword = "Andy";
        final String secondPredicateKeyword = "Andy Lee";

        StaffNameContainsKeywordsPredicate firstPredicate =
                new StaffNameContainsKeywordsPredicate(firstPredicateKeyword);
        StaffNameContainsKeywordsPredicate secondPredicate =
                new StaffNameContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(new StaffNameContainsKeywordsPredicate(firstPredicateKeyword)));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different types -> returns false
        assertFalse(firstPredicate.equals("Hello"));

        // different staff name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeyword_returnsTrue() {
        // without spaces
        StaffNameContainsKeywordsPredicate predicate =
                new StaffNameContainsKeywordsPredicate("Andy");
        assertTrue(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // with spaces
        predicate = new StaffNameContainsKeywordsPredicate("Andy Lau");
        assertTrue(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // mixed-case keywords
        predicate = new StaffNameContainsKeywordsPredicate("anDY");
        assertTrue(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnFalse() {

        // Zero keywords
        StaffNameContainsKeywordsPredicate predicate =
                new StaffNameContainsKeywordsPredicate("");
        assertFalse(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // No matching keywords
        predicate = new StaffNameContainsKeywordsPredicate("Carlos");
        assertFalse(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // Keywords matches staff title, but does not match name
        predicate = new StaffNameContainsKeywordsPredicate("Secretary");
        assertFalse(predicate.test(new StaffBuilder().withStaffName("Andy Lau")
                .withStaffDepartment("HR").withStaffTitle("Secretary")
                .withStaffContact("82572816").withStaffLeave("false").build()));
    }
}
