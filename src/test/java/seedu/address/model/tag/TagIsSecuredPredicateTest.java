package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class TagIsSecuredPredicateTest {

    @Test
    public void test_keywordSecured() {

        TagIsSecuredPredicate predicate = new TagIsSecuredPredicate(new Tag("SECURED"));
        assertTrue(predicate.test(new PersonBuilder().withTags("SECURED").build()));

        assertFalse(predicate.test(new PersonBuilder().withTags("POTENTIAL").build()));

        assertTrue(predicate.test(new PersonBuilder().withTags("SECURED", "POTENTIAL").build()));
    }

    @Test
    public void test_keywordPotential() {

        TagIsSecuredPredicate predicate = new TagIsSecuredPredicate(new Tag("POTENTIAL"));
        assertFalse(predicate.test(new PersonBuilder().withTags("SECURED").build()));

        assertTrue(predicate.test(new PersonBuilder().withTags("POTENTIAL").build()));

        assertTrue(predicate.test(new PersonBuilder().withTags("SECURED", "POTENTIAL").build()));
    }

    @Test
    public void equals() {
        TagIsSecuredPredicate firstPredicate = new TagIsSecuredPredicate(new Tag("SECURED"));
        TagIsSecuredPredicate secondPredicate = new TagIsSecuredPredicate(new Tag("POTENTIAL"));

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagIsSecuredPredicate firstPredicateCopy = new TagIsSecuredPredicate(new Tag("SECURED"));
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different keyword -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }
}
