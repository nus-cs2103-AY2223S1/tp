package seedu.address.model.teammate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TeammateBuilder;

public class ContainsTagPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateTagList = Collections.singletonList("frontend");
        List<String> secondPredicateTagList = Arrays.asList("frontend", "backend");

        ContainsTagPredicate firstPredicate = new ContainsTagPredicate(firstPredicateTagList);
        ContainsTagPredicate secondPredicate = new ContainsTagPredicate(secondPredicateTagList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ContainsTagPredicate firstPredicateCopy = new ContainsTagPredicate(firstPredicateTagList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different list -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_teammateHasTag_returnsTrue() {
        // One tag
        ContainsTagPredicate predicate = new ContainsTagPredicate(Collections.singletonList("frontend"));
        assertTrue(predicate.test(new TeammateBuilder().withTags("frontend").build()));

        // Multiple tags
        predicate = new ContainsTagPredicate(Arrays.asList("frontend", "backend"));
        assertTrue(predicate.test(new TeammateBuilder().withTags("frontend", "backend").build()));

        // Only one matching tag
        predicate = new ContainsTagPredicate(Arrays.asList("frontend", "backend"));
        assertTrue(predicate.test(new TeammateBuilder().withTags("backend", "fullstack").build()));

        // Mixed-case tags
        predicate = new ContainsTagPredicate(Arrays.asList("frontend", "backend"));
        assertTrue(predicate.test(new TeammateBuilder().withTags("fRonTEnd", "baCKEnd").build()));
    }

    @Test
    public void test_teammateDoesNotHaveTag_returnsFalse() {
        // Zero tags
        ContainsTagPredicate predicate = new ContainsTagPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TeammateBuilder().withTags("frontend").build()));

        // No matching tags
        predicate = new ContainsTagPredicate(List.of("fullstack"));
        assertFalse(predicate.test(new TeammateBuilder().withTags("frontend", "backend").build()));

        // Incomplete tags
        predicate = new ContainsTagPredicate(List.of("front"));
        assertFalse(predicate.test(new TeammateBuilder().withTags("frontend", "backend").build()));

    }
}
