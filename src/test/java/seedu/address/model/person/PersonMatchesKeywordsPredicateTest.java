package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonMatchesKeywordsPredicateBuilder;

public class PersonMatchesKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "first";
        String secondPredicateKeywordList = "first second";

        PersonMatchesKeywordsPredicate firstPredicate = new PersonMatchesKeywordsPredicate();
        firstPredicate.setKeywords(firstPredicateKeywordList);
        PersonMatchesKeywordsPredicate secondPredicate = new PersonMatchesKeywordsPredicate();
        secondPredicate.setKeywords(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonMatchesKeywordsPredicate firstPredicateCopy = new PersonMatchesKeywordsPredicate();
        firstPredicateCopy.setKeywords(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // One keyword
        predicate.setKeywords("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate.setKeywords("Alice Bob");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate.setKeywords("Bob Carol");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate.setKeywords("aLIce bOB");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("alice bob").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // Zero keywords
        predicate.setKeywords("  ");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate.setKeywords("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
        assertFalse(predicate.test(new PersonBuilder().withName("John Doe").build()));

        // Keywords match phone, email and address, but does not match name
        predicate.setKeywords("12345 alice@email.com Main Street");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withAddress("Main Street").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // One keyword
        predicate.setKeywords("Clementi");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Clementi").build()));

        // Multiple keywords
        predicate.setKeywords("Clementi Ave 1");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Clementi Ave 1").build()));

        // Only one matching keyword
        predicate.setKeywords("Clementi");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Clementi Ave 1").build()));
        assertTrue(predicate.test(new PersonBuilder().withAddress("36 Clementi Rd").build()));

        // Mixed-case keywords
        predicate.setKeywords("cLeMenTi");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Clementi Ave").build()));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi").build()));
        assertTrue(predicate.test(new PersonBuilder().withAddress("clementi Ave").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // Zero keywords
        predicate.setKeywords("  ");
        assertFalse(predicate.test(new PersonBuilder().withAddress("Clementi").build()));

        // Non-matching keyword
        predicate.setKeywords("Clementi");
        assertFalse(predicate.test(new PersonBuilder().withName("Johor Bahru").build()));
        assertFalse(predicate.test(new PersonBuilder().withName("Kent Ridge").build()));

        // Keywords match other fields but not location
        PersonMatchesKeywordsPredicate predicateOne = PersonMatchesKeywordsPredicateBuilder.buildUserPredicate();
        assertFalse(predicateOne.test(new PersonBuilder().withName("Amy Bee").withAddress("Main Street").build()));
    }

    @Test
    public void test_roleContainsKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // One keyword
        predicate.setKeywords("SWE");
        assertTrue(predicate.test(new PersonBuilder().withRole("SWE").build()));

        // Multiple keywords
        predicate.setKeywords("Software Engineer");
        assertTrue(predicate.test(new PersonBuilder().withRole("Software Engineer").build()));

        // Only one matching keyword
        predicate.setKeywords("Engineer");
        assertTrue(predicate.test(new PersonBuilder().withRole("Software Engineer").build()));
        assertTrue(predicate.test(new PersonBuilder().withRole("Frontend Engineer").build()));

        // Mixed-case keywords
        predicate.setKeywords("eNginEEr");
        assertTrue(predicate.test(new PersonBuilder().withRole("Software Engineer").build()));
        assertTrue(predicate.test(new PersonBuilder().withRole("software Engineer").build()));
        assertTrue(predicate.test(new PersonBuilder().withRole("Frontend engineer").build()));
    }

    @Test
    public void test_roleDoesNotContainKeywords_returnsFalse() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // Zero keywords
        predicate.setKeywords("  ");
        assertFalse(predicate.test(new PersonBuilder().withRole("Engineer").build()));

        // Non-matching keyword
        predicate.setKeywords("Engineer");
        assertFalse(predicate.test(new PersonBuilder().withRole("Product Manager").build()));
        assertFalse(predicate.test(new PersonBuilder().withRole("UI Designer").build()));

        // Keywords match other fields but not role
        PersonMatchesKeywordsPredicate predicateOne = PersonMatchesKeywordsPredicateBuilder.buildUserPredicate();
        assertFalse(predicateOne.test(new PersonBuilder().withName("Amy Bee").withRole("UI Designer").build()));
    }

    @Test
    public void test_gitHubUserContainsKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // One keyword
        predicate.setKeywords("shan");
        assertTrue(predicate.test(new PersonBuilder().withGithubUser("shanhash").build()));


        // Mixed-case keywords
        predicate.setKeywords("shan");
        assertTrue(predicate.test(new PersonBuilder().withGithubUser("ShAn").build()));
        assertTrue(predicate.test(new PersonBuilder().withGithubUser("shaN").build()));
    }

    @Test
    public void test_gitHubUserDoesNotContainKeywords_returnsFalse() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // Zero keywords
        predicate.setKeywords("  ");
        assertFalse(predicate.test(new PersonBuilder().withGithubUser("shan").build()));

        // Non-matching keyword
        predicate.setKeywords("shan");
        assertFalse(predicate.test(new PersonBuilder().withGithubUser("sh4nH").build()));


        // Keywords match other fields but not role
        PersonMatchesKeywordsPredicate predicateOne = PersonMatchesKeywordsPredicateBuilder.buildUserPredicate();
        assertFalse(predicateOne.test(new PersonBuilder().withName("Amy Bee").withGithubUser("shan").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // One keyword
        predicate.setKeywords("friend");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend").build()));

        // Multiple tags
        predicate.setKeywords("friend");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "owesMoney").build()));

        // Only one matching keyword
        predicate.setKeywords("friend");
        assertTrue(predicate.test(new PersonBuilder().withTags("bestFriend").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("closeFriend", "owesMoney").build()));

        // Mixed-case keywords
        predicate.setKeywords("frIeND");
        assertTrue(predicate.test(new PersonBuilder().withTags("friend").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("bestFriend").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("CloseFriend").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        PersonMatchesKeywordsPredicate predicate = new PersonMatchesKeywordsPredicate();
        // Zero keywords
        predicate.setKeywords("  ");
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // Non-matching keyword
        predicate.setKeywords("friend");
        assertFalse(predicate.test(new PersonBuilder().withTags("owesMoney").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("family").build()));

        // Keywords match other fields but not role
        PersonMatchesKeywordsPredicate predicateOne = PersonMatchesKeywordsPredicateBuilder.buildUserPredicate();
        assertFalse(predicateOne.test(new PersonBuilder().withName("Amy Bee").withTags("family").build()));
    }
}
