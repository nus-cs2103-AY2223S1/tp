package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_BAD_BUYER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_GOOD_BUYER;

import seedu.address.model.remark.Remark;

/**
 * A utility class containing a list of {@code Remark} objects to be used in tests.
 */
public class TypicalRemark {

    public static final Remark GOOD_SELLER = new RemarkBuilder().withText("Good Seller").build();
    public static final Remark BAD_SELLER = new RemarkBuilder().withText("Bad Seller").build();
    public static final Remark FAST_DEAL = new RemarkBuilder().withText("Fast Deal").build();
    public static final Remark LONG_REMARK = new RemarkBuilder()
            .withText("Seller was very knowledgeable, friendly and easygoing. He was willing "
                    + "to travel to my convenience too.").build();
    public static final Remark SHORT_REMARK = new RemarkBuilder().withText("good").build();
    public static final Remark MEETING_LOCATION = new RemarkBuilder().withText("Bedok MRT").build();

    // Manually added - Remark's details found in {@code CommandTestUtil}
    public static final Remark GOOD_BUYER = new RemarkBuilder().withText(VALID_TEXT_GOOD_BUYER).build();
    public static final Remark BAD_BUYER = new RemarkBuilder().withText(VALID_TEXT_BAD_BUYER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRemark() {} // prevents instantiation

}
