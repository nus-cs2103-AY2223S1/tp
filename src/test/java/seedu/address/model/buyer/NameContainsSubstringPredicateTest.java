package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class NameContainsSubstringPredicateTest {

    @Test
    public void test_nameContainsSubstring_returnsTrue() {
        NameContainsSubstringPredicate predicate;

        // Mixed-case substring of one word
        predicate = new NameContainsSubstringPredicate("aLiC");
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        // Mixed-case substring across words
        predicate = new NameContainsSubstringPredicate("aLiCe b");
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainSubstring_returnsFalse() {
        NameContainsSubstringPredicate predicate;

        predicate = new NameContainsSubstringPredicate("aLiC l");
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        predicate = new NameContainsSubstringPredicate("aLiCm");
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));
    }
}
