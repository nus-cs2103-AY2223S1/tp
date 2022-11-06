package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.TUTOR1;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.tutor.Tutor;


public class TutorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        HashMap<Prefix, String> firstPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "rachel");
        firstPredicateKeywords.put(PREFIX_ADDRESS, "");
        firstPredicateKeywords.put(PREFIX_EMAIL, "rachel@mail");
        firstPredicateKeywords.put(PREFIX_PHONE, "12348765");
        firstPredicateKeywords.put(PREFIX_QUALIFICATION, "");
        firstPredicateKeywords.put(PREFIX_INSTITUTION, "");
        firstPredicateKeywords.put(PREFIX_TAG, "");

        HashMap<Prefix, String> secondPredicateKeywords = new HashMap<>();
        secondPredicateKeywords.put(PREFIX_NAME, "jackie");
        secondPredicateKeywords.put(PREFIX_ADDRESS, "woodlands");
        secondPredicateKeywords.put(PREFIX_EMAIL, "");
        secondPredicateKeywords.put(PREFIX_PHONE, "");
        firstPredicateKeywords.put(PREFIX_QUALIFICATION, "computing");
        firstPredicateKeywords.put(PREFIX_INSTITUTION, "nus");
        secondPredicateKeywords.put(PREFIX_TAG, "");

        TutorContainsKeywordsPredicate<Tutor> firstPredicate =
                new TutorContainsKeywordsPredicate<>(firstPredicateKeywords);
        TutorContainsKeywordsPredicate<Tutor> secondPredicate =
                new TutorContainsKeywordsPredicate<>(secondPredicateKeywords);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorContainsKeywordsPredicate<Tutor> firstPredicateCopy =
                new TutorContainsKeywordsPredicate<>(firstPredicateKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_invalidType_throwsClassCastException() {
        HashMap<Prefix, String> firstPredicateKeywords = new HashMap<>();
        firstPredicateKeywords.put(PREFIX_NAME, "aaron");
        firstPredicateKeywords.put(PREFIX_ADDRESS, "pioneer");
        firstPredicateKeywords.put(PREFIX_EMAIL, "aaron@mail.com");
        firstPredicateKeywords.put(PREFIX_PHONE, "12345678");
        firstPredicateKeywords.put(PREFIX_QUALIFICATION, "computing");
        firstPredicateKeywords.put(PREFIX_INSTITUTION, "nus");
        firstPredicateKeywords.put(PREFIX_TAG, "good");

        TutorContainsKeywordsPredicate<String> firstPredicate =
                new TutorContainsKeywordsPredicate<>(firstPredicateKeywords);
        assertThrows(ClassCastException.class, () -> firstPredicate.test("Invalid type"));
    }

    @Test
    public void test_tutorContainsKeywords_returnsTrue() {
        // One keyword
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "alice");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");


        TutorContainsKeywordsPredicate<Tutor> predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(TUTOR1));

        // Multiple keywords
        keywords.put(PREFIX_ADDRESS, "jurong");
        keywords.put(PREFIX_EMAIL, "example.com");
        keywords.put(PREFIX_PHONE, "94351253");
        keywords.put(PREFIX_INSTITUTION, "national university");
        keywords.put(PREFIX_TAG, "friends");

        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(TUTOR1));

        // Mixed-case keywords
        keywords.put(PREFIX_QUALIFICATION, "CoMputINg");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertTrue(predicate.test(TUTOR1));
    }

    @Test
    public void test_tutorDoesNotContainKeywords_returnsFalse() {

        // Non-matching keyword
        // Name
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "bob");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");
        TutorContainsKeywordsPredicate<Tutor> predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Address
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "clementi");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Email
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "bob@mail.com");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Phone
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "12344321");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Qualification
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "engineering");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Institution
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "Nanyang Technological University");
        keywords.put(PREFIX_TAG, "");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Tag
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_QUALIFICATION, "");
        keywords.put(PREFIX_INSTITUTION, "");
        keywords.put(PREFIX_TAG, "bad");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));

        // Keywords match everything but name
        keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "Charlie");
        keywords.put(PREFIX_ADDRESS, "jurong");
        keywords.put(PREFIX_EMAIL, "example.com");
        keywords.put(PREFIX_PHONE, "94351253");
        keywords.put(PREFIX_QUALIFICATION, "Bachelor of Computing");
        keywords.put(PREFIX_INSTITUTION, "National University of Singapore");
        keywords.put(PREFIX_TAG, "friends");
        predicate = new TutorContainsKeywordsPredicate<>(keywords);
        assertFalse(predicate.test(TUTOR1));
    }
}
