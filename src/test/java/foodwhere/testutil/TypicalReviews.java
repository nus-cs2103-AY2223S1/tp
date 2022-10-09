package foodwhere.testutil;

import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DETAIL_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DETAIL_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import foodwhere.model.AddressBook;
import foodwhere.model.review.Review;

/**
 * A utility class containing a list of {@code Review} objects to be used in tests.
 */
public class TypicalReviews {

    public static final Review ALICE = new ReviewBuilder().withName("Alice Pauline")
            .withContent("123, Jurong West Ave 6, #08-111")
            .withDetails("friends").build();
    public static final Review BENSON = new ReviewBuilder().withName("Benson Meier")
            .withContent("311, Clementi Ave 2, #02-25")
            .withDetails("owesMoney", "friends").build();
    public static final Review CARL = new ReviewBuilder().withName("Carl Kurz")
            .withContent("wall street").build();
    public static final Review DANIEL = new ReviewBuilder().withName("Daniel Meier")
            .withContent("10th street").withDetails("friends").build();
    public static final Review ELLE = new ReviewBuilder().withName("Elle Meyer")
            .withContent("michegan ave").build();
    public static final Review FIONA = new ReviewBuilder().withName("Fiona Kunz")
            .withContent("little tokyo").build();
    public static final Review GEORGE = new ReviewBuilder().withName("George Best")
            .withContent("4th street").build();

    // Manually added
    public static final Review HOON = new ReviewBuilder().withName("Hoon Meier")
            .withContent("little india").build();
    public static final Review IDA = new ReviewBuilder().withName("Ida Mueller")
            .withContent("chicago ave").build();

    // Manually added - Stall's details found in {@code CommandTestUtil}
    public static final Review AMY = new ReviewBuilder().withName(VALID_NAME_AMY)
            .withContent(VALID_ADDRESS_AMY).withDetails(VALID_DETAIL_FRIEND).build();
    public static final Review BOB = new ReviewBuilder().withName(VALID_NAME_BOB)
            .withContent(VALID_ADDRESS_BOB).withDetails(VALID_DETAIL_HUSBAND, VALID_DETAIL_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalReviews() {} // prevents instantiation

    public static List<Review> getTypicalReviews() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
