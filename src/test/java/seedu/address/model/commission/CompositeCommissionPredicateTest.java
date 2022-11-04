package seedu.address.model.commission;

import static seedu.address.testutil.TypicalCustomers.ALICE;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CommissionBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompositeCommissionPredicateTest {
    @Test
    public void equals() {
        List<String> firstKeywordList = Collections.singletonList("chainsaw");
        List<String> secondKeywordList = Arrays.asList("hungry", "skull");

        List<Tag> firstMustTagList = Arrays.asList(new Tag("tag1"), new Tag("tag2"));
        List<Tag> secondMustTagList = Arrays.asList(new Tag("tag2"), new Tag("tag3"));

        List<Tag> firstOptionalTagList = Arrays.asList(new Tag("tag4"), new Tag("tag5"));
        List<Tag> secondOptionalTagList = Arrays.asList(new Tag("tag4"), new Tag("tag6"));

        CompositeCommissionPredicate firstPredicate = new CompositeCommissionPredicate(firstKeywordList,
                firstMustTagList, firstOptionalTagList);
        CompositeCommissionPredicate secondPredicate = new CompositeCommissionPredicate(secondKeywordList,
                secondMustTagList, secondOptionalTagList);

        assertTrue(firstPredicate.equals(firstPredicate));

        CompositeCommissionPredicate firstPredicateCopy = new CompositeCommissionPredicate(firstKeywordList,
                firstMustTagList, firstOptionalTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));

        assertFalse(firstPredicate.equals(null));

        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywordsAndTags_returnsTrue() {
        List<String> keywordList = Arrays.asList("Ichigo", "Bleach");
        List<Tag> mustTagList = Arrays.asList(new Tag("anime"), new Tag("illustration"));
        List<Tag> optionalTagList = Arrays.asList(new Tag("cinematic"), new Tag("fight"));
        CompositeCommissionPredicate predicate = new CompositeCommissionPredicate(
                keywordList, mustTagList, optionalTagList);
        // Meets all
        assertTrue(predicate.test(new CommissionBuilder().withTitle("Ichigo Bleach")
                .withTags("anime", "illustration", "cinematic", "fight").build(ALICE)));

        // Meets at least one keyword and all the other requirements
        assertTrue(predicate.test(new CommissionBuilder().withTitle("Ichigo brown")
                .withTags("anime", "illustration", "cinematic", "fight").build(ALICE)));

        // Meets at least one optional tag requirement
        assertTrue(predicate.test(new CommissionBuilder().withTitle("Ichigo")
                .withTags("anime", "illustration", "cinematic").build(ALICE)));

        // More than the required tags
        assertTrue(predicate.test(new CommissionBuilder().withTitle("Ichigo Bleach hagen")
                .withTags("anime", "illustration", "cinematic", "fight", "demanding").build(ALICE)));

        // Mixed case keyword
        assertTrue(predicate.test(new CommissionBuilder().withTitle("Ichigo")
                .withTags("anime", "illustration", "cinematic").build(ALICE)));
    }

    @Test
    public void test_nameDoesNotContainKeywordsAndTags_returnFalse() {
        List<String> keywordList = Arrays.asList("Ichigo", "Bleach");
        List<Tag> mustTagList = Arrays.asList(new Tag("anime"), new Tag("illustration"));
        List<Tag> optionalTagList = Arrays.asList(new Tag("cinematic"), new Tag("fight"));
        CompositeCommissionPredicate predicate = new CompositeCommissionPredicate(
                keywordList, mustTagList, optionalTagList);

        // Fails keyword
        assertFalse(predicate.test(new CommissionBuilder().withTitle("lebron")
                .withTags("anime", "illustration", "cinematic").build(ALICE)));

        // Fails must have tag
        assertFalse(predicate.test(new CommissionBuilder().withTitle("Bleach")
                .withTags("anime", "cinematic", "fight").build(ALICE)));

        // Fails optional tag
        assertFalse(predicate.test(new CommissionBuilder().withTitle("Ichigo")
                .withTags("anime", "illustration").build(ALICE)));

        // Fails all
        assertFalse(predicate.test(new CommissionBuilder().withTitle("Samuel")
                .withTags("enemy", "tall", "mad").build(ALICE)));
    }
}
