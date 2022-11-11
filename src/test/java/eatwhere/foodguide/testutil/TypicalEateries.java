package eatwhere.foodguide.testutil;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_CUISINE_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_CUISINE_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;

/**
 * A utility class containing a list of {@code Eatery} objects to be used in tests.
 */
public class TypicalEateries {

    public static final Eatery ALICE = new EateryBuilder().withName("Alice Abalone")
            .withLocation("University Town")
            .withCuisine("Chinese")
            .withPrice("$")
            .withTags("seafood").build();
    public static final Eatery BENSON = new EateryBuilder().withName("Benson Bar")
            .withLocation("University Town")
            .withCuisine("Beverages")
            .withPrice("$$")
            .withTags("alcohol").build();
    public static final Eatery CARL = new EateryBuilder().withName("Carl Cafeteria")
            .withLocation("Block S3, Faculty of Science")
            .withCuisine("Western")
            .withPrice("$$$")
            .withTags("steak").build();
    public static final Eatery DANIEL = new EateryBuilder().withName("Daniel Diner")
            .withLocation("Block S4, Faculty of Science")
            .withCuisine("Mixed")
            .withPrice("$")
            .withTags("steak").build();
    public static final Eatery ELLE = new EateryBuilder().withName("Elle Eatery")
            .withLocation("Block E5, Faculty of Engineering")
            .withCuisine("Mixed")
            .withPrice("$$").build();
    public static final Eatery FIONA = new EateryBuilder().withName("Fiona Fastfood")
            .withLocation("Block E6, Faculty of Engineering")
            .withCuisine("Western")
            .withPrice("$$$").build();
    public static final Eatery GEORGE = new EateryBuilder().withName("George Grill")
            .withLocation("Block A7, Faculty of Arts")
            .withCuisine("Western")
            .withPrice("$").build();

    // Manually added
    public static final Eatery HOON = new EateryBuilder().withName("Hoon Hamburgers")
            .withLocation("University Town")
            .withCuisine("Snacks")
            .withPrice("$$").build();
    public static final Eatery IDA = new EateryBuilder().withName("Ida Indian")
            .withLocation("Block S9, Faculty of Science")
            .withCuisine("Indian")
            .withPrice("$$$").build();

    // Manually added - Eatery's details found in {@code CommandTestUtil}
    public static final Eatery AMY = new EateryBuilder().withName(VALID_NAME_AMY)
            .withLocation(VALID_ADDRESS_AMY)
            .withCuisine(VALID_CUISINE_AMY)
            .withPrice(VALID_PRICE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Eatery BOB = new EateryBuilder().withName(VALID_NAME_BOB)
            .withLocation(VALID_ADDRESS_BOB)
            .withCuisine(VALID_CUISINE_BOB)
            .withPrice(VALID_PRICE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    private TypicalEateries() {} // prevents instantiation

    /**
     * Returns an {@code FoodGuide} with all the typical eateries.
     */
    public static FoodGuide getTypicalFoodGuide() {
        FoodGuide fg = new FoodGuide();
        for (Eatery eatery : getTypicalEateries()) {
            fg.addEatery(eatery);
        }
        return fg;
    }

    public static List<Eatery> getTypicalEateries() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
