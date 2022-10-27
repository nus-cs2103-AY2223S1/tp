//package seedu.address.model.person;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.testutil.PersonBuilder;
//
//public class NameContainsKeywordsPredicateTest {
//
//    @Test
//    public void equals() {
//        String firstPredicateKeywordList = "first";
//        String secondPredicateKeywordList = "first second";
//
//        PersonMatchesKeywordsPredicate firstPredicate = new PersonMatchesKeywordsPredicate(firstPredicateKeywordList);
//        PersonMatchesKeywordsPredicate secondPredicate = new PersonMatchesKeywordsPredicate(
//                secondPredicateKeywordList);
//
//        // same object -> returns true
//        assertTrue(firstPredicate.equals(firstPredicate));
//
//        // same values -> returns true
//        PersonMatchesKeywordsPredicate firstPredicateCopy = new PersonMatchesKeywordsPredicate(
//                firstPredicateKeywordList);
//        assertTrue(firstPredicate.equals(firstPredicateCopy));
//
//        // different types -> returns false
//        assertFalse(firstPredicate.equals(1));
//
//        // null -> returns false
//        assertFalse(firstPredicate.equals(null));
//
//        // different person -> returns false
//        assertFalse(firstPredicate.equals(secondPredicate));
//    }
//
//    @Test
//    public void test_nameContainsKeywords_returnsTrue() {
//        // One keyword
//        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate("Alice");
//        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//
//        // Multiple keywords
//        predicate = new PersonMatchesKeywordsPredicate("AliceBob");
//        assertTrue(predicate.test(new PersonBuilder().withName("AliceBob").build()));
//
//        // Only one matching keyword
//        predicate = new PersonMatchesKeywordsPredicate("BobCarol");
//        assertTrue(predicate.test(new PersonBuilder().withName("AliceCarol").build()));
//
//        // Mixed-case keywords
//        predicate = new PersonMatchesKeywordsPredicate("aLIcebOB");
//        assertTrue(predicate.test(new PersonBuilder().withName("AliceBob").build()));
//    }
//
//    @Test
//    public void test_nameDoesNotContainKeywords_returnsFalse() {
//        // Zero keywords
//        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate("");
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
//
//        // Non-matching keyword
//        predicate = new PersonMatchesKeywordsPredicate("Carol");
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
//
//        // Keywords match phone, email and address, but does not match name
//        predicate = new PersonMatchesKeywordsPredicate("12345 alice@email.com Main Street");
//        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withAddress("Main Street").build()));
//    }
//}
