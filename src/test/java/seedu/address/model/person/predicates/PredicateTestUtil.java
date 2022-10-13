package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.function.Predicate;

/**
 * Contains helper methods for testing Predicates.
 */
public class PredicateTestUtil {

    /**
     * Checks that 2 Predicates are equal to each other according to their equals() method.
     *
     * @param firstPredicate A predicate to compare
     * @param firstPredicateCopy A copy of the firstPredicate that should be equal to {@code firstPredicate}
     * @param secondPredicate Another predicate that should be not equal to {@code firstPredicate}
     * @param <T>
     */
    public static <T extends Predicate<?>> void assertBasicEqualities(T firstPredicate, T firstPredicateCopy,
                                                                      T secondPredicate) {
        // same object -> equals
        assertEquals(firstPredicate, firstPredicate);

        // same values -> equals
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> not equal
        assertNotEquals(null, firstPredicate);

        // different search info -> not equal
        assertNotEquals(firstPredicate, secondPredicate);
    }
}
