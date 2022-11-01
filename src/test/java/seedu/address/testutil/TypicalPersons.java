package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RACE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RACE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELIGION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELIGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SURVEY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SURVEY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withGender("female").withBirthdate("1998-12-28")
            .withRace("White American").withReligion("Christian").withSurvey("Environment Survey", false)
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withGender("male").withBirthdate("1981-1-23")
            .withRace("African American").withReligion("Christian").withSurvey("Shopping Survey", true)
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withGender("others").withBirthdate("1991-1-15")
            .withRace("White American").withReligion("Buddhist").withSurvey("Touring Survey", false)
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withGender("male").withBirthdate("2001-11-23")
            .withRace("Hispanic").withReligion("Christian").withSurvey("Shopping Survey", false)
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withGender("female").withBirthdate("1993-11-1")
            .withRace("White American").withReligion("Free Thinker").withSurvey("Touring Survey", false)
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withGender("female").withBirthdate("1991-8-13")
            .withRace("White American").withReligion("Christian").withSurvey("Food Survey", false)
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withGender("male").withBirthdate("1991-12-28")
            .withRace("White American").withReligion("Christian").withSurvey("Environment Survey", false)
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withGender(VALID_GENDER_AMY)
            .withBirthdate(VALID_BIRTHDATE_AMY).withRace(VALID_RACE_AMY).withReligion(VALID_RELIGION_AMY)
            .withSurvey(VALID_SURVEY_AMY, false).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withGender(VALID_GENDER_BOB)
            .withBirthdate(VALID_BIRTHDATE_BOB).withRace(VALID_RACE_BOB).withReligion(VALID_RELIGION_BOB)
            .withSurvey(VALID_SURVEY_BOB, false).withTags(VALID_TAG_FRIEND).build();

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
