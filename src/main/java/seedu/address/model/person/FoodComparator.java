package seedu.address.model.person;

import java.util.Comparator;

/**
 * A comparator class to sort the {@code Food} in the order of breakfast, lunch then dinner
 * and in the order in which the user input the {@code Food}.
 */
public class FoodComparator implements Comparator<Food> {
    @Override
    public int compare(Food food1, Food food2) {
        switch (food1.getEarliestMealTag() + food2.getEarliestMealTag()) {
        case "BB":
        case "LL":
        case "DD":
        case "XX":
            return food1.isAfter(food2) ? 1 : -1;
        case "BL":
        case "BD":
        case "BX":
        case "LD":
        case "LX":
        case "DX":
            return -1;
        case "LB":
        case "DB":
        case "DL":
        case "XB":
        case "XL":
        case "XD":
            return 1;
        default:
            return 0;
        }
    }
}
