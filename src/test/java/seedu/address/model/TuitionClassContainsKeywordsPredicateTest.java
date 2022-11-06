package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.tuitionclass.TuitionClass;

public class TuitionClassContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        HashMap<Prefix, String> firstPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "P2Math");
        firstPredicateKeywords.put(PREFIX_DAY, "");
        firstPredicateKeywords.put(PREFIX_SUBJECT_OR_SCHOOL, "Mathematics");
        firstPredicateKeywords.put(PREFIX_LEVEL, "primary 2");
        firstPredicateKeywords.put(PREFIX_TIME, "18:00-20:00");
        firstPredicateKeywords.put(PREFIX_TAG, "");

        HashMap<Prefix, String> secondPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "P5English");
        firstPredicateKeywords.put(PREFIX_DAY, "Sunday");
        firstPredicateKeywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        firstPredicateKeywords.put(PREFIX_LEVEL, "");
        firstPredicateKeywords.put(PREFIX_TIME, "");
        firstPredicateKeywords.put(PREFIX_TAG, "easy");

        TuitionClassContainsKeywordsPredicate<TuitionClass> firstPredicate =
                new TuitionClassContainsKeywordsPredicate<>(firstPredicateKeywords);
        TuitionClassContainsKeywordsPredicate<TuitionClass> secondPredicate =
                new TuitionClassContainsKeywordsPredicate<>(secondPredicateKeywords);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TuitionClassContainsKeywordsPredicate<TuitionClass> firstPredicateCopy =
                new TuitionClassContainsKeywordsPredicate<>(firstPredicateKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tuition class -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_invalidType_throwsClassCastException() {
        HashMap<Prefix, String> firstPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "P2Math");
        firstPredicateKeywords.put(PREFIX_DAY, "");
        firstPredicateKeywords.put(PREFIX_SUBJECT_OR_SCHOOL, "Mathematics");
        firstPredicateKeywords.put(PREFIX_LEVEL, "primary 2");
        firstPredicateKeywords.put(PREFIX_TIME, "18:00-20:00");
        firstPredicateKeywords.put(PREFIX_TAG, "");

        TuitionClassContainsKeywordsPredicate<String> firstPredicate =
                new TuitionClassContainsKeywordsPredicate<>(firstPredicateKeywords);
        assertThrows(ClassCastException.class, () -> firstPredicate.test("Invalid type"));
    }

    @Test
    public void test_tuitionClassContainsKeywords_returnsTrue() {
        // One keyword
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "P2Math");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "");


        TuitionClassContainsKeywordsPredicate<TuitionClass> predicate =
                new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(TUITIONCLASS1));

        // Multiple keywords
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "math");
        keywords.put(PREFIX_LEVEL, "primary 2");
        keywords.put(PREFIX_TIME, "10:00");
        keywords.put(PREFIX_TAG, "tough");

        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(TUITIONCLASS1));

        // Mixed-case keywords
        keywords.put(PREFIX_DAY, "mOnDay");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(TUITIONCLASS1));
    }

    @Test
    public void test_tuitionClassDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        // Name
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "S4Chemistry");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "");
        TuitionClassContainsKeywordsPredicate<TuitionClass> predicate =
                new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));

        // Day
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_DAY, "tuesday");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));

        // Subject
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "chemistry");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));

        // Level
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "secondary 4");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));

        // Time
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "18:00-20:00");
        keywords.put(PREFIX_TAG, "");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));

        // Tag
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_DAY, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TIME, "");
        keywords.put(PREFIX_TAG, "easy");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));

        // Keywords match everything but name
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "S4Chemistry");
        keywords.put(PREFIX_DAY, "monday");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "math");
        keywords.put(PREFIX_LEVEL, "primary 2");
        keywords.put(PREFIX_TIME, "10:00-12:00");
        keywords.put(PREFIX_TAG, "tough");
        predicate = new TuitionClassContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUITIONCLASS1));
    }
}
