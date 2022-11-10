package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.CustomerBuilder;

public class CustomerContainsAllTagPredicateTest {
    @Test
    public void equals() {
        List<Tag> firstPredicateTagList = Collections.singletonList(new Tag("tag1"));
        List<Tag> secondPredicateTagList = Arrays.asList(new Tag("tag1"), new Tag("tag2"));

        CustomerContainsAllTagPredicate firstPredicate = new CustomerContainsAllTagPredicate(firstPredicateTagList);
        CustomerContainsAllTagPredicate secondPredicate = new CustomerContainsAllTagPredicate(secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CustomerContainsAllTagPredicate firstPredicateCopy = new CustomerContainsAllTagPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsTags_returnsTrue() {
        // One Tag
        CustomerContainsAllTagPredicate predicate =
                new CustomerContainsAllTagPredicate(Collections.singletonList(new Tag("tag1")));
        assertTrue(predicate.test(new CustomerBuilder().withTags("tag1", "tag2").build()));

        // Multiple Tags
        predicate = new CustomerContainsAllTagPredicate(Arrays.asList(new Tag("tag1"), new Tag("tag2")));
        assertTrue(predicate.test(new CustomerBuilder().withTags("tag1", "tag2").build()));

        // Mixed-case Tags
        predicate = new CustomerContainsAllTagPredicate(Arrays.asList(new Tag("tag1"), new Tag("tag2")));
        assertTrue(predicate.test(new CustomerBuilder().withTags("tag1", "tag2", "tag3").build()));

        // Zero Tags
        predicate = new CustomerContainsAllTagPredicate(Collections.emptyList());
        assertTrue(predicate.test(new CustomerBuilder().withTags("tag1").build()));
    }

    @Test
    public void test_nameDoesNotContainTags_returnsFalse() {
        //Non existent tag
        CustomerContainsAllTagPredicate predicate = new CustomerContainsAllTagPredicate(Arrays.asList(new Tag("tag3")));
        assertFalse(predicate.test(new CustomerBuilder().withTags("tag1", "tag2").build()));

        // Only one matching Tag
        predicate = new CustomerContainsAllTagPredicate(Arrays.asList(new Tag("tag2"), new Tag("tag3")));
        assertFalse(predicate.test(new CustomerBuilder().withTags("tag1", "tag3").build()));
    }
}
