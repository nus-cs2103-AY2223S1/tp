package longtimenosee.testutil;

import static longtimenosee.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_INCOME_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_INCOME_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_RISK_APPETITE_AMY;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_RISK_APPETITE_BOB;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import longtimenosee.model.AddressBook;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@gmail.com")
            .withPhone("94351253")
            .withTags("friends")
            .withBirthday("2018-06-05")
            .withIncome("100000.00")
            .withRiskAppetite("M")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@yahoo.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withBirthday("2017-05-05")
            .withIncome("200.00")
            .withRiskAppetite("M")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@yahoo.com").withAddress("wall street")
            .withBirthday("2016-06-05")
            .withIncome("25000.00")
            .withRiskAppetite("L")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@gmail.com").withAddress("10th street")
            .withBirthday("2019-06-05")
            .withIncome("70000.00")
            .withRiskAppetite("H")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@gmail.com").withAddress("michegan ave")
            .withBirthday("2019-05-05")
            .withIncome("300000.00")
            .withRiskAppetite("L")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withBirthday("2019-06-07")
            .withIncome("150000.00")
            .withRiskAppetite("H")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withBirthday("2022-06-05")
            .withIncome("1000000.00")
            .withRiskAppetite("M")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@yahoo.com").withAddress("little india")
            .withBirthday("2019-06-05")
            .withIncome("100000.00")
            .withRiskAppetite("H")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@hotmail.com").withAddress("chicago ave")
            .withBirthday("2019-06-05")
            .withIncome("50000.00")
            .withRiskAppetite("L")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withBirthday(VALID_BIRTHDAY_AMY).withIncome(VALID_INCOME_AMY)
            .withRiskAppetite(VALID_RISK_APPETITE_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withBirthday(VALID_BIRTHDAY_BOB).withIncome(VALID_INCOME_BOB)
            .withRiskAppetite(VALID_RISK_APPETITE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    //Since the deleteCommand now depends on events containing Bob, we must ensure a deletion of Bob purges this
    public static final Event EVENT_BENSON = new EventBuilder().withName("Benson Meier")
            .withDate("2011-01-01")
            .withDescription("Meet Benson")
            .withDuration("12:00__13:00").build();

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        ab.addEvent(EVENT_BENSON);
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
