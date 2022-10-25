package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameStartsWithKeywordPredicateTest {

    @Test
    public void equals() {
        NameStartsWithKeywordPredicate firstPredicate =
                new NameStartsWithKeywordPredicate(VALID_NAME_AMY);
        NameStartsWithKeywordPredicate secondPredicate =
                new NameStartsWithKeywordPredicate(VALID_NAME_BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same keyword -> returns true
        NameStartsWithKeywordPredicate firstPredicateCopy =
                new NameStartsWithKeywordPredicate(VALID_NAME_AMY);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameStartsWithKeyword_returnsTrue() {
        // One keyword
        NameStartsWithKeywordPredicate predicate = new NameStartsWithKeywordPredicate("Amy");
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_NAME_AMY).build()));

        // Mixed-case keyword
        predicate = new NameStartsWithKeywordPredicate("aMy");
        assertTrue(predicate.test(new PersonBuilder().withName(VALID_NAME_AMY).build()));
    }

    @Test
    public void test_nameDoesNotStartsWithKeyword_returnsFalse() {
        // Zero keywords
        NameStartsWithKeywordPredicate predicate = new NameStartsWithKeywordPredicate(" ");
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_NAME_AMY).build()));

        // Non-matching keyword
        predicate = new NameStartsWithKeywordPredicate("Bob");
        assertFalse(predicate.test(new PersonBuilder().withName(VALID_NAME_AMY).build()));
    }
}
