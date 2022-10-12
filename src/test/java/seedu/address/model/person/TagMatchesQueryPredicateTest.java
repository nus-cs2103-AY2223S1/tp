package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagMatchesQueryPredicateTest {

    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");
        TagMatchesQueryPredicate firstPredicate = new TagMatchesQueryPredicate(firstTag);
        TagMatchesQueryPredicate secondPredicate = new TagMatchesQueryPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagMatchesQueryPredicate firstPredicateCopy = new TagMatchesQueryPredicate(firstTag);
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
        // One keyword
        TagMatchesQueryPredicate predicate = new TagMatchesQueryPredicate(new Tag("first"));
        assertTrue(predicate.test(new PersonBuilder().withTags("first").build()));

        // Mixed-case keywords
        predicate = new TagMatchesQueryPredicate(new Tag("FiRst"));
        assertFalse(predicate.test(new PersonBuilder().withName("first").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagMatchesQueryPredicate predicate = new TagMatchesQueryPredicate(new Tag("first"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match name, phone, email and address, but does not match tag
        Person testPerson = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("second").build();

        predicate = new TagMatchesQueryPredicate(new Tag("Alice"));
        assertFalse(predicate.test(testPerson));

        predicate = new TagMatchesQueryPredicate(new Tag("email"));
        assertFalse(predicate.test(testPerson));

        predicate = new TagMatchesQueryPredicate(new Tag("Street"));
        assertFalse(predicate.test(testPerson));
    }
}
