package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.address.testutil.FoodBuilder.DEFAULT_LATER_TIME;
import static seedu.address.testutil.TypicalPersons.APPLE;
import static seedu.address.testutil.TypicalPersons.BREAD;
import static seedu.address.testutil.TypicalPersons.GRAPES;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FoodBuilder;

public class FoodComparatorTest {
    private final FoodComparator foodComparator = new FoodComparator();

    @Test
    public void compare() {
        // breakfast before lunch
        assertEquals(-1, foodComparator.compare(APPLE, BREAD));

        // lunch after breakfast
        assertEquals(1, foodComparator.compare(BREAD, APPLE));

        // tagged meal before untagged
        assertEquals(-1, foodComparator.compare(APPLE, GRAPES));

        // same tag, food recorded earlier before food recorded later
        Food earlierApple = new FoodBuilder(APPLE).withDateTime(DEFAULT_EARLIER_TIME).build();
        Food laterApple = new FoodBuilder(APPLE).withDateTime(DEFAULT_LATER_TIME).build();
        assertEquals(-1, foodComparator.compare(earlierApple, laterApple));
    }
}
