package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ELEPHANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ELEPHANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.commission.Commission;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCommissions {

    public static final Commission CAT = new CommissionBuilder().withTitle(VALID_TITLE_CAT)
            .withDescription(VALID_DESCRIPTION_CAT).withCompletionStatus(false).withFee(2.0)
            .withTags(VALID_TAG_ANIMAL).build();
    public static final Commission DOG = new CommissionBuilder().withTitle(VALID_TITLE_DOG)
            .withDescription(VALID_DESCRIPTION_DOG).withCompletionStatus(true).withFee(1.0)
            .withTags(VALID_TAG_ANIMAL).build();
    public static final Commission ELEPHANT = new CommissionBuilder().withTitle(VALID_TITLE_ELEPHANT)
            .withDescription(VALID_DESCRIPTION_ELEPHANT).withCompletionStatus(true).build();

    private TypicalCommissions() {} // prevents instantiation

    public static List<Commission> getTypicalCommissions() {
        return new ArrayList<>(Arrays.asList(CAT, DOG, ELEPHANT));
    }
}
