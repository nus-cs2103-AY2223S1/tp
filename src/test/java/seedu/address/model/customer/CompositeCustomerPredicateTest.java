package seedu.address.model.customer;

import org.junit.jupiter.api.Test;
import seedu.address.model.commission.CompositeCustomerPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CustomerBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompositeCustomerPredicateTest {
    @Test
    public void equals() {
        List<String> firstKeywordList = Collections.singletonList("chainsaw");
        List<String> secondKeywordList = Arrays.asList("hungry", "skull");

        List<Tag> firstMustTagList = Arrays.asList(new Tag("tag1"), new Tag("tag2"));
        List<Tag> secondMustTagList = Arrays.asList(new Tag("tag2"), new Tag("tag3"));

        List<Tag> firstOptionalTagList = Arrays.asList(new Tag("tag4"), new Tag("tag5"));
        List<Tag> secondOptionalTagList = Arrays.asList(new Tag("tag4"), new Tag("tag6"));

        CompositeCustomerPredicate firstPredicate = new CompositeCustomerPredicate(firstKeywordList,
                firstMustTagList, firstOptionalTagList);
        CompositeCustomerPredicate secondPredicate = new CompositeCustomerPredicate(secondKeywordList,
                secondMustTagList, secondOptionalTagList);

        assertTrue(firstPredicate.equals(firstPredicate));

        CompositeCustomerPredicate firstPredicateCopy = new CompositeCustomerPredicate(firstKeywordList,
                firstMustTagList, firstOptionalTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));

        assertFalse(firstPredicate.equals(null));

        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywordsAndTags_returnsTrue() {
        List<String> keywordList = Arrays.asList("joseph", "frank");
        List<Tag> mustTagList = Arrays.asList(new Tag("friend"), new Tag("handsome"));
        List<Tag> optionalTagList = Arrays.asList(new Tag("smart"), new Tag("poor"));
        CompositeCustomerPredicate predicate = new CompositeCustomerPredicate(
                keywordList, mustTagList, optionalTagList);
        // Meets all
        assertTrue(predicate.test(new CustomerBuilder().withName("joseph frank")
                .withTags("friend", "handsome", "smart", "poor").build()));

        // Meets at least one keyword and all the other requirements
        assertTrue(predicate.test(new CustomerBuilder().withName("joseph brown")
                .withTags("friend", "handsome", "smart", "poor").build()));

        // Meets at least one optional tag requirement
        assertTrue(predicate.test(new CustomerBuilder().withName("joseph")
                .withTags("friend", "handsome", "smart").build()));

        // More than the required tags
        assertTrue(predicate.test(new CustomerBuilder().withName("joseph frank hagen")
                .withTags("friend", "handsome", "smart", "poor", "demanding").build()));

        // Mixed case keyword
        assertTrue(predicate.test(new CustomerBuilder().withName("JosEph")
                .withTags("friend", "handsome", "smart").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywordsAndTags_returnFalse() {
        List<String> keywordList = Arrays.asList("joseph", "frank");
        List<Tag> mustTagList = Arrays.asList(new Tag("friend"), new Tag("handsome"));
        List<Tag> optionalTagList = Arrays.asList(new Tag("smart"), new Tag("poor"));
        CompositeCustomerPredicate predicate = new CompositeCustomerPredicate(
                keywordList, mustTagList, optionalTagList);

        // Fails keyword
        assertFalse(predicate.test(new CustomerBuilder().withName("lebron")
                .withTags("friend", "handsome", "smart").build()));

        // Fails must have tag
        assertFalse(predicate.test(new CustomerBuilder().withName("frank")
                .withTags("friend", "smart", "poor").build()));

        // Fails optional tag
        assertFalse(predicate.test(new CustomerBuilder().withName("joseph")
                .withTags("friend", "handsome").build()));

        // Fails all
        assertFalse(predicate.test(new CustomerBuilder().withName("Samuel")
                .withTags("enemy", "tall", "mad").build()));
    }
}
