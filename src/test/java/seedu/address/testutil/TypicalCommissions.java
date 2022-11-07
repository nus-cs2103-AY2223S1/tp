package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ELEPHANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PENGUIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SHARK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WHALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ZEBRA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BIRD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CARNIVORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HERBIVORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAMMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MARINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ELEPHANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PENGUIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_SHARK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_WHALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ZEBRA;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.CARL;
import static seedu.address.testutil.TypicalCustomers.DANIEL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import seedu.address.model.AddressBook;
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
            .withTags(VALID_TAG_ANIMAL, VALID_TAG_PET).toCommissionBuilder();
    public static final Commission.CommissionBuilder DOG_BUILDER = new CommissionBuilder()
            .withTitle(VALID_TITLE_DOG).withDeadline(LocalDate.of(2022, 12, 25))
            .withDescription(VALID_DESCRIPTION_DOG).withCompletionStatus(true).withFee(1.0)
            .withTags(VALID_TAG_ANIMAL, VALID_TAG_PET).toCommissionBuilder();
    public static final Commission.CommissionBuilder ELEPHANT_BUILDER = new CommissionBuilder()
            .withTitle(VALID_TITLE_ELEPHANT).withDeadline(LocalDate.of(2022, 12, 24))
            .withDescription(VALID_DESCRIPTION_ELEPHANT)
            .withCompletionStatus(true).toCommissionBuilder();
    public static final Function<Customer, Commission> CAT_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_CAT).withDeadline(LocalDate.of(2021, 1, 1))
            .withDescription(VALID_DESCRIPTION_CAT)
            .withCompletionStatus(false).withFee(2.0)
            .withTags(VALID_TAG_ANIMAL, VALID_TAG_PET).build(customer);
    public static final Function<Customer, Commission> DOG_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_DOG).withDeadline(LocalDate.of(2022, 12, 25))
            .withDescription(VALID_DESCRIPTION_DOG).withCompletionStatus(true).withFee(1.0)
            .withTags(VALID_TAG_ANIMAL, VALID_TAG_PET).build(customer);
    public static final Function<Customer, Commission> ELEPHANT_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_ELEPHANT).withDeadline(LocalDate.of(2022, 12, 24))
            .withDescription(VALID_DESCRIPTION_ELEPHANT).withTags(VALID_TAG_LAND, VALID_TAG_HERBIVORE)
            .withCompletionStatus(true).build(customer);
    public static final Function<Customer, Commission> PENGUIN_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_PENGUIN).withDeadline(LocalDate.of(2022, 11, 15))
            .withDescription(VALID_DESCRIPTION_PENGUIN).withTags(VALID_TAG_ANIMAL, VALID_TAG_BIRD, VALID_TAG_MARINE)
            .withCompletionStatus(true).build(customer);
    public static final Function<Customer, Commission> SHARK_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_SHARK).withDeadline(LocalDate.of(2022, 9, 17))
            .withDescription(VALID_DESCRIPTION_SHARK).withTags(VALID_TAG_MARINE, VALID_TAG_CARNIVORE, VALID_TAG_FISH)
            .withCompletionStatus(true).build(customer);
    public static final Function<Customer, Commission> ZEBRA_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_ZEBRA).withDeadline(LocalDate.of(2022, 5, 3))
            .withDescription(VALID_DESCRIPTION_ZEBRA).withTags(VALID_TAG_HERBIVORE, VALID_TAG_MAMMAL, VALID_TAG_ANIMAL)
            .withCompletionStatus(false).build(customer);
    public static final Function<Customer, Commission> WHALE_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_WHALE).withDeadline(LocalDate.of(2022, 7, 19))
            .withDescription(VALID_DESCRIPTION_WHALE).withTags(VALID_TAG_MAMMAL, VALID_TAG_MARINE, VALID_TAG_ANIMAL)
            .withCompletionStatus(true).build(customer);
    public static final Function<Customer, Commission> DEER_PRODUCER = customer -> new CommissionBuilder()
            .withTitle(VALID_TITLE_DEER).withDeadline(LocalDate.of(2022, 6, 12))
            .withDescription(VALID_DESCRIPTION_DEER).withTags(VALID_TAG_MAMMAL, VALID_TAG_LAND, VALID_TAG_ANIMAL)
            .withCompletionStatus(false).build(customer);

    public static final Commission ALICE_CAT = CAT_PRODUCER.apply(ALICE);
    public static final Commission ALICE_DEER = DEER_PRODUCER.apply(ALICE);
    public static final Commission BENSON_DOG = DOG_PRODUCER.apply(BENSON);
    public static final Commission BENSON_PENGUIN = PENGUIN_PRODUCER.apply(BENSON);
    public static final Commission CARL_ELEPHANT = ELEPHANT_PRODUCER.apply(CARL);
    public static final Commission CARL_SHARK = SHARK_PRODUCER.apply(CARL);
    public static final Commission DANIEL_WHALE = WHALE_PRODUCER.apply(DANIEL);
    public static final Commission DANIEL_ZEBRA = ZEBRA_PRODUCER.apply(DANIEL);




    private TypicalCommissions() {} // prevents instantiation

    public static List<Commission> getTypicalCommissions() {
        return new ArrayList<>(Arrays.asList(ALICE_CAT, BENSON_DOG, CARL_ELEPHANT));
    }

    public static List<Customer> getTypicalCustomersWithCommissions() {
        Customer aliceWithCom = new CustomerBuilder(ALICE).build();
        Customer bensonWithCom = new CustomerBuilder(BENSON).build();
        Customer carlWithCom = new CustomerBuilder(CARL).build();
        Customer danielWithCom = new CustomerBuilder(DANIEL).build();

        aliceWithCom.addCommission(CAT_PRODUCER.apply(aliceWithCom));
        aliceWithCom.addCommission(DEER_PRODUCER.apply(aliceWithCom));
        bensonWithCom.addCommission(DOG_PRODUCER.apply(bensonWithCom));
        bensonWithCom.addCommission(PENGUIN_PRODUCER.apply(bensonWithCom));
        carlWithCom.addCommission(ELEPHANT_PRODUCER.apply(carlWithCom));
        carlWithCom.addCommission(SHARK_PRODUCER.apply(carlWithCom));
        danielWithCom.addCommission(WHALE_PRODUCER.apply(danielWithCom));
        danielWithCom.addCommission(ZEBRA_PRODUCER.apply(danielWithCom));

        return new ArrayList<>(Arrays.asList(aliceWithCom, bensonWithCom, carlWithCom, danielWithCom));
    }

    public static AddressBook getTypicalAddressBookWithCommissions() {
        AddressBook ab = new AddressBook();
        for (Customer customer : getTypicalCustomersWithCommissions()) {
            ab.addCustomer(customer);
        }
        return ab;
    }
}
