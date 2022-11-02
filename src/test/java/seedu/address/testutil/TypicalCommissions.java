package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ELEPHANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ELEPHANT;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.CARL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

/**
 * A utility class containing a list of {@code Commission} objects to be used in tests.
 */
public class TypicalCommissions {

    public static final Commission.CommissionBuilder CAT_BUILDER = new CommissionBuilder()
            .withTitle(VALID_TITLE_CAT).withDeadline(LocalDate.of(2021, 1, 1))
            .withDescription(VALID_DESCRIPTION_CAT)
            .withCompletionStatus(false).withFee(2.0)
            .withTags(VALID_TAG_ANIMAL).toCommissionBuilder();
    public static final Commission.CommissionBuilder DOG_BUILDER = new CommissionBuilder()
            .withTitle(VALID_TITLE_DOG).withDeadline(LocalDate.of(2022, 12, 25))
            .withDescription(VALID_DESCRIPTION_DOG).withCompletionStatus(true).withFee(1.0)
            .withTags(VALID_TAG_ANIMAL).toCommissionBuilder();
    public static final Commission.CommissionBuilder ELEPHANT_BUILDER = new CommissionBuilder()
            .withTitle(VALID_TITLE_ELEPHANT).withDeadline(LocalDate.of(2022, 12, 24))
            .withDescription(VALID_DESCRIPTION_ELEPHANT)
            .withCompletionStatus(true).toCommissionBuilder();
    public static final Function<Customer, Commission> CAT_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_CAT).withDeadline(LocalDate.of(2021, 1, 1))
            .withDescription(VALID_DESCRIPTION_CAT)
            .withCompletionStatus(false).withFee(2.0)
            .withTags(VALID_TAG_ANIMAL).build(customer);
    public static final Function<Customer, Commission> DOG_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_DOG).withDeadline(LocalDate.of(2022, 12, 25))
            .withDescription(VALID_DESCRIPTION_DOG).withCompletionStatus(true).withFee(1.0)
            .withTags(VALID_TAG_ANIMAL).build(customer);
    public static final Function<Customer, Commission> ELEPHANT_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_ELEPHANT).withDeadline(LocalDate.of(2022, 12, 24))
            .withDescription(VALID_DESCRIPTION_ELEPHANT)
            .withCompletionStatus(true).build(customer);
    public static final Commission ALICE_CAT = CAT_PRODUCER.apply(ALICE);
    public static final Commission BENSON_DOG = DOG_PRODUCER.apply(BENSON);
    public static final Commission CARL_ELEPHANT = ELEPHANT_PRODUCER.apply(CARL);

    private TypicalCommissions() {} // prevents instantiation

    public static List<Commission> getTypicalCommissions() {
        return new ArrayList<>(Arrays.asList(ALICE_CAT, BENSON_DOG, CARL_ELEPHANT));
    }
}
