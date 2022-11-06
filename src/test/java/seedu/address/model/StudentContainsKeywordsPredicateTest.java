package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.STUDENT1;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.student.Student;

public class StudentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        HashMap<Prefix, String> firstPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "melvin");
        firstPredicateKeywords.put(PREFIX_ADDRESS, "");
        firstPredicateKeywords.put(PREFIX_EMAIL, "melvin@mail");
        firstPredicateKeywords.put(PREFIX_PHONE, "");
        firstPredicateKeywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        firstPredicateKeywords.put(PREFIX_LEVEL, "");
        firstPredicateKeywords.put(PREFIX_TAG, "");

        HashMap<Prefix, String> secondPredicateKeywords = new HashMap<>();
        secondPredicateKeywords.put(PREFIX_NAME, "");
        secondPredicateKeywords.put(PREFIX_ADDRESS, "");
        secondPredicateKeywords.put(PREFIX_EMAIL, "");
        secondPredicateKeywords.put(PREFIX_PHONE, "");
        secondPredicateKeywords.put(PREFIX_SUBJECT_OR_SCHOOL, "Keming");
        secondPredicateKeywords.put(PREFIX_LEVEL, "Secondary 1");
        secondPredicateKeywords.put(PREFIX_TAG, "");

        StudentContainsKeywordsPredicate<Student> firstPredicate =
                new StudentContainsKeywordsPredicate<>(firstPredicateKeywords);
        StudentContainsKeywordsPredicate<Student> secondPredicate =
                new StudentContainsKeywordsPredicate<>(secondPredicateKeywords);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentContainsKeywordsPredicate<Student> firstPredicateCopy =
                new StudentContainsKeywordsPredicate<>(firstPredicateKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_invalidType_throwsClassCastException() {
        HashMap<Prefix, String> firstPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "alice");
        firstPredicateKeywords.put(PREFIX_ADDRESS, "clementi");
        firstPredicateKeywords.put(PREFIX_EMAIL, "");
        firstPredicateKeywords.put(PREFIX_PHONE, "");
        firstPredicateKeywords.put(PREFIX_SUBJECT_OR_SCHOOL, "Keming");
        firstPredicateKeywords.put(PREFIX_LEVEL, "Secondary 1");
        firstPredicateKeywords.put(PREFIX_TAG, "");

        StudentContainsKeywordsPredicate<String> firstPredicate =
                new StudentContainsKeywordsPredicate<>(firstPredicateKeywords);
        assertThrows(ClassCastException.class, () -> firstPredicate.test("Invalid type"));
    }

    @Test
    public void test_studentContainsKeywords_returnsTrue() {
        // One keyword
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "alice");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");


        StudentContainsKeywordsPredicate<Student> predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(STUDENT1));

        // Multiple keywords
        keywords.put(PREFIX_ADDRESS, "jurong");
        keywords.put(PREFIX_EMAIL, "example.com");
        keywords.put(PREFIX_PHONE, "94351253");
        keywords.put(PREFIX_LEVEL, "primary 3");
        keywords.put(PREFIX_TAG, "friends");

        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(STUDENT1));

        // Mixed-case keywords
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "KEmIng");

        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(STUDENT1));
    }

    @Test
    public void test_studentDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        // Name
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "bob");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        StudentContainsKeywordsPredicate<Student> predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // Address
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "clementi");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // Email
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "bob@mail.com");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // Phone
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "94351299");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // School
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "rulang");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // Level
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "secondary 1");
        keywords.put(PREFIX_TAG, "");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // Tag
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "bad");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));

        // Keywords match everything but name
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "Charlie");
        keywords.put(PREFIX_ADDRESS, "jurong");
        keywords.put(PREFIX_EMAIL, "example.com");
        keywords.put(PREFIX_PHONE, "94351253");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "Keming");
        keywords.put(PREFIX_LEVEL, "primary 3");
        keywords.put(PREFIX_TAG, "friends");
        predicate = new StudentContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(STUDENT1));
    }
}
