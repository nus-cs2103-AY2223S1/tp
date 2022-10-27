package seedu.foodrem.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_NUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;

import org.junit.jupiter.api.Test;

import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.TagBuilder;

public class TagSetContainsTagPredicateTest {
    private final Tag numbersTag = new TagBuilder()
            .withTagName(VALID_TAG_NAME_NUMBERS)
            .build();
    private final Tag vegetableTag = new TagBuilder()
            .withTagName(VALID_TAG_NAME_VEGETABLES)
            .build();

    @Test
    public void equals() {
        TagSetContainsTagPredicate containsNumbersTagPred = new TagSetContainsTagPredicate(numbersTag);
        TagSetContainsTagPredicate containsVegetableTagPred = new TagSetContainsTagPredicate(vegetableTag);

        // Equals self
        assertEquals(containsNumbersTagPred, containsNumbersTagPred);

        // Same value -> Returns true
        TagSetContainsTagPredicate containsNumbersTagPredCopy = new TagSetContainsTagPredicate(numbersTag);
        assertEquals(containsNumbersTagPred, containsNumbersTagPredCopy);

        // Diff value -> Returns false
        assertNotEquals(containsNumbersTagPred, containsVegetableTagPred);

        // Diff class -> Returns false
        assertNotEquals(containsNumbersTagPred, "Should be not equal");

        // null -> Returns false
        assertNotEquals(null, containsNumbersTagPred);
    }

    @Test
    public void test_tagSetContainsTag_returnsTrue() {
        TagSetContainsTagPredicate containsNumbersTagPred = new TagSetContainsTagPredicate(numbersTag);
        TagSetContainsTagPredicate containsVegetableTagPred = new TagSetContainsTagPredicate(vegetableTag);

        assertTrue(containsNumbersTagPred.test(new ItemBuilder().withTags(VALID_TAG_NAME_NUMBERS).build()));

        assertTrue(containsVegetableTagPred.test(new ItemBuilder().withTags(VALID_TAG_NAME_VEGETABLES).build()));
    }

    @Test
    public void test_tagSetNotContainsTag_returnsFalse() {
        TagSetContainsTagPredicate containsVegetableTagPred = new TagSetContainsTagPredicate(vegetableTag);

        // Item with no tags
        assertFalse(containsVegetableTagPred.test(new ItemBuilder().build()));

        // Item with different tag
        assertFalse(containsVegetableTagPred.test(new ItemBuilder().withTags(VALID_TAG_NAME_NUMBERS).build()));
    }
}
