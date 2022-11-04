package seedu.hrpro.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hrpro.testutil.StaffBuilder;

public class StaffNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        final List<String> firstPredicateKeyword = Collections.singletonList("Andy");
        final List<String> secondPredicateKeyword = Arrays.asList("Andy", "Lee");

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
                new StaffNameContainsKeywordsPredicate(Collections.singletonList("Andy"));
        assertTrue(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // with spaces
        predicate = new StaffNameContainsKeywordsPredicate(Arrays.asList("Andy Lau"));
        assertTrue(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // mixed-case keywords
        predicate = new StaffNameContainsKeywordsPredicate(Collections.singletonList("anDY"));
        assertTrue(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnFalse() {

        // Zero keywords
        StaffNameContainsKeywordsPredicate predicate =
                new StaffNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // No matching keywords
        predicate = new StaffNameContainsKeywordsPredicate(Arrays.asList("Carlos"));
        assertFalse(predicate.test(new StaffBuilder().withStaffName("Andy Lau").build()));

        // Keywords matches staff title and department, but does not match name
        predicate = new StaffNameContainsKeywordsPredicate(Arrays.asList("Secretary", "HR"));
        assertFalse(predicate.test(new StaffBuilder().withStaffName("Andy Lau")
                .withStaffDepartment("HR").withStaffTitle("Secretary")
                .withStaffContact("82572816").withStaffLeave("false").build()));
    }
}
