package foodwhere.testutil;

import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_CONTENT_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_CONTENT_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;

/**
 * A utility class containing a list of {@code Review} objects to be used in tests.
 */
public class TypicalReviews {

    public static final Review ALICE = new ReviewBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withDate("01/01/2020")
            .withContent("Great view and decor!")
            .withRating(3)
            .withTags("friends").build();
    public static final Review BENSON = new ReviewBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withDate("31/12/2021")
            .withContent("The food is warm and nourishing.")
            .withRating(3)
            .withTags("owesMoney", "friends").build();
    public static final Review CARL = new ReviewBuilder().withName("Carl Kurz")
            .withAddress("wall street")
            .withDate("01/04/2020").withRating(3).withContent("wall street").build();
    public static final Review DANIEL = new ReviewBuilder().withName("Daniel Meier")
            .withAddress("10th street")
            .withDate("29/02/2020").withRating(3).withContent("10th street").withTags("friends").build();
    public static final Review ELLE = new ReviewBuilder().withName("Elle Meyer")
            .withAddress("michegan ave")
            .withDate("29/09/2003").withRating(3).withContent("michegan ave").build();
    public static final Review FIONA = new ReviewBuilder().withName("Fiona Kunz")
            .withAddress("little tokyo")
            .withDate("06/06/2007").withRating(3).withContent("little tokyo").build();
    public static final Review GEORGE = new ReviewBuilder().withName("George Best")
            .withAddress("4th street")
            .withDate("20/07/2017").withRating(3).withContent("4th street").build();

    // Manually added
    public static final Review HOON = new ReviewBuilder().withName("Hoon Meier")
            .withAddress("little india")
            .withDate("25/12/2020").withRating(3).withContent("little india").build();
    public static final Review IDA = new ReviewBuilder().withName("Ida Mueller")
            .withAddress("chicago ave")
            .withDate("09/09/2022").withRating(3).withContent("chicago ave").build();

    // Manually added - Review's details found in {@code CommandTestUtil}
    public static final Review AMY = new ReviewBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withDate(VALID_DATE_AMY).withContent(VALID_CONTENT_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Review BOB = new ReviewBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withDate(VALID_DATE_BOB).withContent(VALID_CONTENT_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalReviews() {} // prevents instantiation

    public static List<Review> getTypicalReviews() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

}
