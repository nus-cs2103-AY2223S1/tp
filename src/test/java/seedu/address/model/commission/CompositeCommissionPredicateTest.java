package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCustomers.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.CommissionBuilder;

public class CompositeCommissionPredicateTest {
    @Test
    public void equals() {
        Set<String> firstKeywordList = Collections.singleton("chainsaw");
        Set<String> secondKeywordList = new HashSet<>(Arrays.asList("hungry", "skull"));

        Set<Tag> firstMustTagList = new HashSet<>(Arrays.asList(new Tag("tag1"), new Tag("tag2")));
        Set<Tag> secondMustTagList = new HashSet<>(Arrays.asList(new Tag("tag2"), new Tag("tag3")));

        Set<Tag> firstOptionalTagList = new HashSet<>(Arrays.asList(new Tag("tag4"), new Tag("tag5")));
        Set<Tag> secondOptionalTagList = new HashSet<>(Arrays.asList(new Tag("tag4"), new Tag("tag6")));

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
        Set<String> keywordList = new HashSet<>(Arrays.asList("Ichigo", "Bleach"));
        Set<Tag> mustTagList = new HashSet<>(Arrays.asList(new Tag("anime"), new Tag("illustration")));
        Set<Tag> optionalTagList = new HashSet<>(Arrays.asList(new Tag("cinematic"), new Tag("fight")));
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
        Set<String> keywordList = new HashSet<>(Arrays.asList("Ichigo", "Bleach"));
        Set<Tag> mustTagList = new HashSet<>(Arrays.asList(new Tag("anime"), new Tag("illustration")));
        Set<Tag> optionalTagList = new HashSet<>(Arrays.asList(new Tag("cinematic"), new Tag("fight")));
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
