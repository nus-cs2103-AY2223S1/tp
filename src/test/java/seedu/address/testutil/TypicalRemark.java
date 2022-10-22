package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import seedu.address.model.remark.Remark;

/**
 * A utility class containing a list of {@code Remark} objects to be used in tests.
 */
public class TypicalRemark {

    public static final Remark ALICE = new RemarkBuilder().withName("Alice Pauline").withAddress("4th Street")
            .withTags("friends").build();
    public static final Remark BENSON = new RemarkBuilder().withName("Benson Meier").withAddress("5th Avenue")
            .withTags("owesMoney", "friends").build();
    public static final Remark CARL = new RemarkBuilder().withName("Carl Kurz").withAddress("Loyang way").build();
    public static final Remark DANIEL = new RemarkBuilder().withName("Daniel Meier").withAddress("3rd lane").build();
    public static final Remark ELLE = new RemarkBuilder().withName("Elle Meyer").withAddress("Wolf Stress").build();
    public static final Remark FIONA = new RemarkBuilder().withName("Fiona Kunz").withAddress("Bedok MRT").build();
    public static final Remark GEORGE = new RemarkBuilder().withName("George Best")
            .withAddress("Clementi Mall").build();

    // Manually added
    public static final Remark HOON = new RemarkBuilder().withName("Hoon Meier").withAddress("NUS Utown").build();
    public static final Remark IDA = new RemarkBuilder().withName("Ida Mueller").withAddress("Raffle hall").build();

    // Manually added - Remark's details found in {@code CommandTestUtil}
    public static final Remark AMY = new RemarkBuilder().withName(VALID_NAME_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Remark BOB = new RemarkBuilder().withName(VALID_NAME_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRemark() {} // prevents instantiation

}
