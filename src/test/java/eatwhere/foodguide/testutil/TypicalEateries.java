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

    public static final Eatery ALICE = new EateryBuilder().withName("Alice Pauline")
            .withLocation("123, Jurong West Ave 6, #08-111").withCuisine("aliceexamplecom")
            .withPrice("$")
            .withTags("friends").build();
    public static final Eatery BENSON = new EateryBuilder().withName("Benson Meier")
            .withLocation("311, Clementi Ave 2, #02-25")
            .withCuisine("johndexamplecom").withPrice("$$")
            .withTags("owesMoney", "friends").build();
    public static final Eatery CARL = new EateryBuilder().withName("Carl Kurz").withPrice("$$$")
            .withCuisine("heinzexamplecom").withLocation("wall street").build();
    public static final Eatery DANIEL = new EateryBuilder().withName("Daniel Meier").withPrice("$")
            .withCuisine("corneliaexamplecom").withLocation("10th street").withTags("friends").build();
    public static final Eatery ELLE = new EateryBuilder().withName("Elle Meyer").withPrice("$$")
            .withCuisine("wernerexamplecom").withLocation("michegan ave").build();
    public static final Eatery FIONA = new EateryBuilder().withName("Fiona Kunz").withPrice("$$$")
            .withCuisine("lydiaexamplecom").withLocation("little tokyo").build();
    public static final Eatery GEORGE = new EateryBuilder().withName("George Best").withPrice("$")
            .withCuisine("annaexamplecom").withLocation("4th street").build();

    // Manually added
    public static final Eatery HOON = new EateryBuilder().withName("Hoon Meier").withPrice("$$")
            .withCuisine("stefanexamplecom").withLocation("little india").build();
    public static final Eatery IDA = new EateryBuilder().withName("Ida Mueller").withPrice("$$$")
            .withCuisine("hansexamplecom").withLocation("chicago ave").build();

    // Manually added - Eatery's details found in {@code CommandTestUtil}
    public static final Eatery AMY = new EateryBuilder().withName(VALID_NAME_AMY).withPrice(VALID_PRICE_AMY)
            .withCuisine(VALID_CUISINE_AMY).withLocation(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Eatery BOB = new EateryBuilder().withName(VALID_NAME_BOB).withPrice(VALID_PRICE_BOB)
            .withCuisine(VALID_CUISINE_BOB).withLocation(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
