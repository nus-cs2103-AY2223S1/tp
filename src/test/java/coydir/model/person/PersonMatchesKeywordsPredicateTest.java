package coydir.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import coydir.testutil.PersonBuilder;

public class PersonMatchesKeywordsPredicateTest {

    private PersonMatchesKeywordsPredicate preparePredicate(List<String> keywords) {
        while (keywords.size() < 3) {
            keywords.add("");
        }
        return new PersonMatchesKeywordsPredicate(keywords.get(0), keywords.get(1), keywords.get(2));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = new ArrayList<>(Arrays.asList("first", "second", "third"));
        List<String> secondPredicateKeywordList = new ArrayList<>(Collections.singletonList("first"));

        PersonMatchesKeywordsPredicate firstPredicate = preparePredicate(firstPredicateKeywordList);
        PersonMatchesKeywordsPredicate secondPredicate = preparePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonMatchesKeywordsPredicate firstPredicateCopy = preparePredicate(firstPredicateKeywordList);
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
        // Zero keywords
        PersonMatchesKeywordsPredicate predicate = preparePredicate(new ArrayList<>());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One keyword
        predicate = preparePredicate(new ArrayList<>(Collections.singletonList("Alice")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = preparePredicate(new ArrayList<>(Arrays.asList("Alice", "Software Engineer")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                    .withPosition("Software Engineer").build()));

        // Mixed-case keywords
        predicate = preparePredicate(new ArrayList<>(Arrays.asList("aLIce", "SOftWAre EngINeeR", "teCHnoLogy")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                    .withPosition("Software Engineer").withDepartment("Technology").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PersonMatchesKeywordsPredicate predicate = preparePredicate(new ArrayList<>(Arrays.asList("Carol")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = preparePredicate(new ArrayList<>(Arrays.asList("Bob", "Intern")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Carol")
                    .withPosition("Intern").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = preparePredicate(new ArrayList<>(Arrays.asList("12345", "alice@email.com", "Main Street")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
