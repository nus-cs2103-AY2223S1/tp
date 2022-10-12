package foodwhere.testutil;

import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import foodwhere.model.AddressBook;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;

/**
 * A utility class containing a list of {@code Stall} objects to be used in tests.
 */
public class TypicalStalls {

    public static final Stall ALICE = new StallBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends").build();
    public static final Stall BENSON = new StallBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends").build();
    public static final Stall CARL = new StallBuilder().withName("Carl Kurz")
            .withAddress("wall street").build();
    public static final Stall DANIEL = new StallBuilder().withName("Daniel Meier")
            .withAddress("10th street").withTags("friends").build();
    public static final Stall ELLE = new StallBuilder().withName("Elle Meyer")
            .withAddress("michegan ave").build();
    public static final Stall FIONA = new StallBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo").build();
    public static final Stall GEORGE = new StallBuilder().withName("George Best")
            .withAddress("4th street").build();

    // Manually added
    public static final Stall HOON = new StallBuilder().withName("Hoon Meier")
            .withAddress("little india").build();
    public static final Stall IDA = new StallBuilder().withName("Ida Mueller")
            .withAddress("chicago ave").build();

    // Manually added - Stall's details found in {@code CommandTestUtil}
    public static final Stall AMY = new StallBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Stall BOB = new StallBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStalls() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical stalls.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Stall stall : getTypicalStalls()) {
            ab.addStall(stall);
        }
        for (Review review : TypicalReviews.getTypicalReviews()) {
            ab.addReview(review);
        }
        return ab;
    }

    public static List<Stall> getTypicalStalls() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
