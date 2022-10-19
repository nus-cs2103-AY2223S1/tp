package seedu.nutrigoals.model.meal;

import java.util.Comparator;

import seedu.nutrigoals.model.tag.Tag;

/**
 * A comparator class to sort the {@code Food} in the order of breakfast, lunch then dinner
 * and in the order in which the user input the {@code Food}.
 */
public class FoodComparator implements Comparator<Food> {
    @Override
    public int compare(Food food1, Food food2) {
        Tag.TagName tagName1 = food1.getTag().tagName;
        Tag.TagName tagName2 = food2.getTag().tagName;

        if (tagName1 == tagName2) {
            return food1.isAfter(food2) ? 1 : -1;
        } else {
            return tagName1.compareTo(tagName2);
        }
    }
}
