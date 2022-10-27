package seedu.boba.testutil;

import static seedu.boba.logic.commands.CommandTestUtil.VALID_BIRTHDAY_MONTH_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_BIRTHDAY_MONTH_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_MEMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.boba.model.BobaBot;
import seedu.boba.model.customer.Customer;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCustomers {

    public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withBirthdayMonth("1").withReward("123").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Customer ALICE_INCREMENT = new CustomerBuilder().withName("Alice Pauline")
            .withReward("223").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Customer ALICE_DECREMENT = new CustomerBuilder().withName("Alice Pauline")
            .withReward("23").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
            .withBirthdayMonth("2").withReward("456")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withBirthdayMonth("3").withReward("789").build();
    public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withBirthdayMonth("4").withReward("101112").withTags("friends").build();
    public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("94822241")
            .withEmail("werner@example.com").withBirthdayMonth("5").withReward("131415").build();
    public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("lydia@example.com").withBirthdayMonth("6").withReward("161718").build();
    public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("94824421")
            .withEmail("anna@example.com").withBirthdayMonth("7").withReward("192021").build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withName("Hoon Meier").withPhone("84824241")
            .withEmail("stefan@example.com").withBirthdayMonth("8").withReward("222324").build();
    public static final Customer IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("84821311")
            .withEmail("hans@example.com").withBirthdayMonth("9").withReward("252627").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withBirthdayMonth(VALID_BIRTHDAY_MONTH_AMY)
            .withReward(VALID_REWARD_AMY).withTags(VALID_TAG_MEMBER).build();
    public static final Customer BOB = new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withBirthdayMonth(VALID_BIRTHDAY_MONTH_BOB)
            .withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCustomers() {} // prevents instantiation

    /**
     * Returns an {@code BobaBot} with all the typical persons.
     */
    public static BobaBot getTypicalBobaBot() {
        BobaBot ab = new BobaBot();
        for (Customer customer : getTypicalPersons()) {
            ab.addPerson(customer);
        }
        return ab;
    }

    public static List<Customer> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
