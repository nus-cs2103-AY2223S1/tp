package eatwhere.foodguide.testutil;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Eatery BENSON = new EateryBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Eatery CARL = new EateryBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Eatery DANIEL = new EateryBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Eatery ELLE = new EateryBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Eatery FIONA = new EateryBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Eatery GEORGE = new EateryBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Eatery HOON = new EateryBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Eatery IDA = new EateryBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Eatery's details found in {@code CommandTestUtil}
    public static final Eatery AMY = new EateryBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Eatery BOB = new EateryBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

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
