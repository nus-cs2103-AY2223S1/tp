package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StudentContainsKeywordsPredicate firstPredicate =
                new StudentContainsKeywordsPredicate(firstPredicateKeywordList);
        StudentContainsKeywordsPredicate secondPredicate =
                new StudentContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentContainsKeywordsPredicate firstPredicateCopy =
                new StudentContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        StudentContainsKeywordsPredicate predicate =
                new StudentContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only partial matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("Bob", "Car"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        StudentContainsKeywordsPredicate predicate =
                new StudentContainsKeywordsPredicate(Collections.singletonList("alice@example.com"));
        assertTrue(predicate.test(new StudentBuilder().withEmail("alice@example.com").build()));

        // Multiple keywords with only one match keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("alice@example.com", "johnd@example.com"));
        assertTrue(predicate.test(new StudentBuilder().withEmail("alice@example.com").build()));

        // Mixed-case keywords
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("alIce@example.com", "aaa"));
        assertTrue(predicate.test(new StudentBuilder().withEmail("alice@example.com").build()));

        // Only partial matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("@example.com", "bob@example.com"));
        assertTrue(predicate.test(new StudentBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withEmail("alice@example.com").build()));

        // Non-matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("bob@example.com"));
        assertFalse(predicate.test(new StudentBuilder().withEmail("alice@example.com").build()));
    }

    @Test
    public void test_classGroupContainsKeywords_returnsTrue() {
        // Exact keyword
        StudentContainsKeywordsPredicate predicate =
                new StudentContainsKeywordsPredicate(Collections.singletonList("cs2030"));
        assertTrue(predicate.test(new StudentBuilder().withClassGroup("cs2030 lab 31").build()));

        // Multiple keywords with only one match keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("cs2030", "aaa", "bb"));
        assertTrue(predicate.test(new StudentBuilder().withClassGroup("cs2030 lab 31").build()));

        // Mixed-case keywords
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("CS2030", "lAb", "31"));
        assertTrue(predicate.test(new StudentBuilder().withClassGroup("cs2030 lab 31").build()));

        // Only partial matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("2030", "ab"));
        assertTrue(predicate.test(new StudentBuilder().withClassGroup("cs2030 lab 31").build()));
    }

    @Test
    public void test_classGroupDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withClassGroup("cs2030 lab 31").build()));

        // Non-matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("cs2103t"));
        assertFalse(predicate.test(new StudentBuilder().withClassGroup("cs2030 lab 31").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        StudentContainsKeywordsPredicate predicate =
                new StudentContainsKeywordsPredicate(Collections.singletonList("12345678"));
        assertTrue(predicate.test(new StudentBuilder().withPhone("12345678").build()));

        // Multiple keywords with only one match keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("12345678", "11111111"));
        assertTrue(predicate.test(new StudentBuilder().withPhone("12345678").build()));

        // Only partial matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("1234", "7"));
        assertTrue(predicate.test(new StudentBuilder().withPhone("12345678").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withPhone("98765678").build()));

        // Non-matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("99999999"));
        assertFalse(predicate.test(new StudentBuilder().withPhone("98765678").build()));
    }

    @Test
    public void test_studentIdContainsKeywords_returnsTrue() {
        // One keyword
        StudentContainsKeywordsPredicate predicate =
                new StudentContainsKeywordsPredicate(Collections.singletonList("e0998765"));
        assertTrue(predicate.test(new StudentBuilder().withStudentId("e0998765").build()));

        // Multiple keywords with only one match keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("e0998765", "e0693225"));
        assertTrue(predicate.test(new StudentBuilder().withStudentId("e0998765").build()));

        // Mixed-case keywords
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("E0998765", "e000000"));
        assertTrue(predicate.test(new StudentBuilder().withStudentId("e0998765").build()));

        // Only partial matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("e099", "65"));
        assertTrue(predicate.test(new StudentBuilder().withStudentId("e0998765").build()));
    }

    @Test
    public void test_studentIdDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withStudentId("e0998765").build()));

        // Non-matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("e0999999"));
        assertFalse(predicate.test(new StudentBuilder().withStudentId("e0998765").build()));
    }
}
