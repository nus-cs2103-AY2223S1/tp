package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_LATER_TIME;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.BREAD;
import static seedu.nutrigoals.testutil.TypicalFoods.GRAPES;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.testutil.FoodBuilder;

public class FoodComparatorTest {
    private final FoodComparator foodComparator = new FoodComparator();

    @Test
    public void compare() {
        // breakfast before lunch
        assertTrue(foodComparator.compare(APPLE, BREAD) < 0);

        // lunch after breakfast
        assertTrue(foodComparator.compare(BREAD, APPLE) > 0);

        // breakfast before dinner
        assertTrue(foodComparator.compare(APPLE, GRAPES) < 0);

        // same tag, food recorded earlier before food recorded later
        Food earlierApple = new FoodBuilder(APPLE).withDateTime(DEFAULT_EARLIER_TIME).build();
        Food laterApple = new FoodBuilder(APPLE).withDateTime(DEFAULT_LATER_TIME).build();
        assertTrue(foodComparator.compare(earlierApple, laterApple) < 0);
    }
}
