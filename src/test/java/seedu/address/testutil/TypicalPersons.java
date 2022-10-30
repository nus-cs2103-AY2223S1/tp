package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTTAG_CURRENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTTAG_POTENTIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_23_MAR_2024;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCOME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_JURONGPOINT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_WESTMALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONTHLY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONTHLY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKTAG_LOW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withIncome("$1000")
            .withMonthly("$200")
            .withRiskTag("LOW")
            .withPlanTag("Savings Plan")
            .withClientTag("POTENTIAL")
            .withTags("friends")
            .build();

    public static final Person MUSAB_WITH_NO_APPT = new PersonBuilder()
            .withName("Musab")
            .withAddress("301 Upper Thomson Rd, Singapore 574408")
            .withEmail("musab@gmail.com")
            .withPhone("92345678")
            .withMonthly("$100")
            .withRiskTag("LOW")
            .withPlanTag("Savings Plan")
            .withClientTag("CURRENT")
            .withTags("gay")
            .build();

    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withIncome("$1000")
            .withMonthly("$100")
            .withRiskTag("HIGH")
            .withPlanTag("Savings Plan")
            .withClientTag("POTENTIAL")
            .withTags("owesMoney", "friends")
            .build();
    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withIncome("$1000")
            .withMonthly("$200")
            .withRiskTag("LOW")
            .withPlanTag("Savings Plan")
            .withClientTag("CURRENT")
            .build();
    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withIncome("$1000")
            .withMonthly("$300")
            .withRiskTag("LOW")
            .withTags("friends")
            .withPlanTag("Savings Plan")
            .withClientTag("CURRENT")
            .build();
    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withIncome("$6000")
            .withMonthly("$250")
            .withRiskTag("MEDIUM")
            .withClientTag("POTENTIAL")
            .withPlanTag("Savings Plan")
            .withAppointment(new AppointmentBuilder()
                    .withDateTime(VALID_DATETIME_22_JAN_2023)
                    .withLocation(VALID_LOCATION_JURONGPOINT).build())
            .build();
    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withIncome("$2000")
            .withMonthly("$500")
            .withRiskTag("MEDIUM")
            .withClientTag("CURRENT")
            .withPlanTag("Savings Plan")
            .withAppointment(new AppointmentBuilder()
                    .withDateTime(VALID_DATETIME_23_MAR_2024)
                    .withLocation(VALID_LOCATION_WESTMALL).build())
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withIncome("$10000")
            .withMonthly("$400")
            .withRiskTag("LOW")
            .withClientTag("POTENTIAL")
            .withPlanTag("Savings Plan")
            .withAppointment(new AppointmentBuilder()
                    .withDateTime(VALID_DATETIME_21_JAN_2023)
                    .withLocation(VALID_LOCATION_NUS).build())
            .withAppointment(new AppointmentBuilder()
                    .withDateTime(VALID_DATETIME_23_MAR_2024)
                    .withLocation(VALID_LOCATION_NUS).build())
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withIncome("$1000")
            .withMonthly("$300")
            .withRiskTag("LOW")
            .withClientTag("CURRENT")
            .withPlanTag("Savings Plan")
            .build();
    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withIncome("$1000")
            .withMonthly("$200")
            .withClientTag("POTENTIAL")
            .withRiskTag("MEDIUM")
            .withPlanTag("Savings Plan")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withIncome(VALID_INCOME_AMY)
            .withMonthly(VALID_MONTHLY_AMY)
            .withRiskTag(VALID_RISKTAG_LOW)
            .withPlanTag("Savings Plan")
            .withClientTag(VALID_CLIENTTAG_CURRENT)
            .withTags(VALID_TAG_FRIEND)
            .build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withIncome(VALID_INCOME_BOB)
            .withMonthly(VALID_MONTHLY_BOB)
            .withRiskTag(VALID_RISKTAG_LOW)
            .withPlanTag("Savings Plan")
            .withClientTag(VALID_CLIENTTAG_POTENTIAL)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
