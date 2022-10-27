package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class ListOfContainsKeywordsPredicatesTest {

    @Test
    void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MajorContainsKeywordsPredicate majorPredicate = new MajorContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        ListOfContainsKeywordsPredicates firstPredicateList = new ListOfContainsKeywordsPredicates(
                new ArrayList<>(Collections.singleton(majorPredicate)));
        ListOfContainsKeywordsPredicates secondPredicateList = new ListOfContainsKeywordsPredicates(
                new ArrayList<>(Collections.singleton(namePredicate)));

        // same object -> returns true
        assertTrue(firstPredicateList.equals(firstPredicateList));

        // same values -> returns true
        ListOfContainsKeywordsPredicates firstPredicateListCopy = new ListOfContainsKeywordsPredicates(
                new ArrayList<>(Collections.singleton(majorPredicate)));
        assertTrue(firstPredicateList.equals(firstPredicateListCopy));

        // different types -> returns false
        assertFalse(firstPredicateList.equals(1));

        // null -> returns false
        assertFalse(firstPredicateList.equals(null));

        // different List -> returns false
        assertFalse(firstPredicateList.equals(secondPredicateList));
    }

    @Test
    void addPredicate() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");

        MajorContainsKeywordsPredicate majorPredicate = new MajorContainsKeywordsPredicate(firstPredicateKeywordList);

        ListOfContainsKeywordsPredicates firstPredicateList = new ListOfContainsKeywordsPredicates(
                new ArrayList<>(Collections.singleton(majorPredicate)));
        ListOfContainsKeywordsPredicates secondPredicateList = new ListOfContainsKeywordsPredicates(
                new ArrayList<>());
        secondPredicateList.addPredicate(majorPredicate);
        assertTrue(firstPredicateList.equals(secondPredicateList));
    }
}
