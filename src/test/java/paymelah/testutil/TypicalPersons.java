package paymelah.testutil;

import static paymelah.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static paymelah.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_DEBT_KARAOKE;
import static paymelah.logic.commands.CommandTestUtil.VALID_DEBT_KOI;
import static paymelah.logic.commands.CommandTestUtil.VALID_DEBT_PIZZA;
import static paymelah.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static paymelah.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static paymelah.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static paymelah.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static paymelah.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static paymelah.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static paymelah.testutil.TypicalDebts.BURGER;
import static paymelah.testutil.TypicalDebts.COOP;
import static paymelah.testutil.TypicalDebts.KFC_BURGER;
import static paymelah.testutil.TypicalDebts.MCDONALDS;
import static paymelah.testutil.TypicalDebts.MEALS;
import static paymelah.testutil.TypicalDebts.SUPPER;
import static paymelah.testutil.TypicalDebts.TAOBAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import paymelah.model.AddressBook;
import paymelah.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withTelegram("alice_handle")
            .withPhone("94351253")
            .withTags("friends").withDebts(MCDONALDS).build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTelegram("johnd_handle").withPhone("98765432")
            .withTags("owesMoney", "friends").withDebts(BURGER).build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withTelegram("heinz_handle").withAddress("wall street").withDebts(COOP).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withTelegram("cornelia_handle").withAddress("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withTelegram("werner_handle").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withTelegram("lydia_handle").withAddress("little tokyo").withDebts(TAOBAO).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withTelegram("anna_handle").withAddress("4th street").withDebts(KFC_BURGER, SUPPER).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withTelegram("stefan_handle").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withTelegram("hans_handle").withAddress("chicago ave").withDebts(MEALS).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTelegram(VALID_TELEGRAM_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withDebts(VALID_DEBT_PIZZA, VALID_DEBT_KARAOKE).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTelegram(VALID_TELEGRAM_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withDebts(VALID_DEBT_KOI, VALID_DEBT_KARAOKE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    public static final String KEYWORD_MATCHING_BURGER = "burger";
    public static final String KEYWORD_NOT_MATCHING_BURGER = "burgers";
    public static final String KEYWORD_MATCHING_MEAL = "meal";

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
