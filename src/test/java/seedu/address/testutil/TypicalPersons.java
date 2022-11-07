package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ANDERSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_COLIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_MALE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ANDERSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_COLIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    /* -------------------------------STUDENT------------------------------- */

    public static final Person AARON = new StudentBuilder().withName("Aaron Tan")
            .withEmail("aaron@example.com").withGender("M")
            .withPhone("85877235").withTags("friends").withGithubUsername("").build();
    public static final Person ALEX = new StudentBuilder().withName("Alex Jones")
            .withEmail("alex@example.com").withGender("M")
            .withPhone("85237238").withTags("friends").build();
    public static final Person ALICE = new StudentBuilder().withYear("4")
            .withName("Alice Pauline")
            .withEmail("alice@example.com").withGender("F").withLocation("JUS")
            .withPhone("94351253").withTags("friends").build();
    public static final Person BENSON = new StudentBuilder().withYear("3")
            .withName("Benson Meier")
            .withGender("M").withLocation("HUK")
            .withEmail("Benm@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withGithubUsername("ben10").build();
    public static final Person CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563").withTags("smart")
            .withLocation("SMU").withEmail("heinz@example.com").withGender("M").withGithubUsername("carlz").build();
    public static final Person DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withLocation("NUS")
            .withEmail("cornelia@example.com")
            .withGender("M").withTags("cool").withGithubUsername("dan").build();
    public static final Person GEORGE = new StudentBuilder().withModuleCodes("CS1000", "CS9999").withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com").withLocation("HKU").withGender("M").build();

    // Manually added
    public static final Person HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withGender("M").build();
    public static final Person IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withGender("F").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = (Student) new StudentBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withGender(VALID_GENDER_AMY).build();
    public static final Professor BOB = (Professor) new ProfessorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withGender(VALID_GENDER_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    public static final TeachingAssistant CABE = (TeachingAssistant) new TeachingAssistantBuilder()
            .withName(VALID_NAME_CABE).withPhone(VALID_PHONE_CABE)
            .withEmail(VALID_EMAIL_CABE).withGender(VALID_GENDER_CABE).withTags(VALID_TAG_FRIEND)
            .withGithubUsername("cabe")
            .withLocation("JIN")
            .build();

    public static final TeachingAssistant JOHN = (TeachingAssistant) new TeachingAssistantBuilder()
            .withName("JOHN").withPhone("12345")
            .withEmail("john@example.com").withGender("M").withTags(VALID_TAG_FRIEND)
            .withGithubUsername("johnny123")
            .withLocation("BMU")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    /* -------------------------------PROFESSOR------------------------------- */
    public static final Professor ANDERSON = (Professor) new ProfessorBuilder().withSpecialisation("Networks")
            .withName(VALID_NAME_ANDERSON)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_ANDERSON)
            .withGender(VALID_GENDER_MALE)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withLocation("JIO")
            .build();
    public static final Professor BEN = (Professor) new ProfessorBuilder().withOfficeHour("MONDAY, 11:00 AM - 01:00 PM")
            .withName(VALID_NAME_BEN)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BEN)
            .withGender(VALID_GENDER_MALE)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withLocation("RUT")
            .withGithubUsername("ben10")
            .build();
    public static final Professor COLIN = (Professor) new ProfessorBuilder()
            .withOfficeHour("TUESDAY, 12:00 PM - 02:00 PM")
            .withName(VALID_NAME_COLIN)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_COLIN)
            .withGender(VALID_GENDER_MALE)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withLocation("ABC")
            .withGithubUsername("callin")
            .build();
    public static final Person ELLE = new ProfessorBuilder().withRating("1").withName("Elle Meyer").withPhone("9482224")
            .withLocation("NTU")
            .withEmail("werner@example.com")
            .withGender("F").withGithubUsername("elle").withTags("cool", "smart").build();

    /* -------------------------------TEACHING ASSISTANT------------------------------- */
    public static final Person FIONA = new TeachingAssistantBuilder().withRating("2").withModuleCode("CS1000")
            .withName("Fiona Kunz").withPhone("9482427").withEmail("lydia@example.com")
            .withGender("F").withLocation("JIK").build();

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

    /**
     * Returns an {@code AddressBook} with typical
     * persons in unsorted order lexicographically.
     */
    public static AddressBook getUnsortedAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addPerson(CABE);
        ab.addPerson(HOON);
        ab.addPerson(FIONA);
        ab.addPerson(ALEX);
        ab.addPerson(BOB);
        ab.addPerson(AARON);
        ab.addPerson(ALICE);
        ab.addPerson(GEORGE);
        ab.addPerson(DANIEL);
        ab.addPerson(AMY);
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with
     * persons in sorted order lexicographically ascending.
     */
    public static AddressBook getSortedAddressBookAscending() {
        AddressBook ab = new AddressBook();
        ab.addPerson(AARON);
        ab.addPerson(ALEX);
        ab.addPerson(ALICE);
        ab.addPerson(AMY);
        ab.addPerson(BOB);
        ab.addPerson(CABE);
        ab.addPerson(DANIEL);
        ab.addPerson(FIONA);
        ab.addPerson(GEORGE);
        ab.addPerson(HOON);
        return ab;
    }
    /**
     * Returns an {@code AddressBook} with
     * persons in sorted order lexicographically descending.
     */
    public static AddressBook getSortedAddressBookDescending() {
        AddressBook ab = new AddressBook();
        ab.addPerson(HOON);
        ab.addPerson(GEORGE);
        ab.addPerson(FIONA);
        ab.addPerson(DANIEL);
        ab.addPerson(CABE);
        ab.addPerson(BOB);
        ab.addPerson(AMY);
        ab.addPerson(ALICE);
        ab.addPerson(ALEX);
        ab.addPerson(AARON);
        return ab;
    }
    /**
     * Returns an {@code AddressBook} with
     * Professors in sorted order lexicographically ascending.
     */
    public static AddressBook getSortedProfessorsAscending() {
        AddressBook ab = new AddressBook();
        ab.addPerson(ANDERSON);
        ab.addPerson(BEN);
        ab.addPerson(BOB);
        ab.addPerson(COLIN);
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with
     * Professors in unsorted order.
     */
    public static AddressBook getUnsortedProfessors() {
        AddressBook ab = new AddressBook();
        ab.addPerson(COLIN);
        ab.addPerson(BOB);
        ab.addPerson(BEN);
        ab.addPerson(ANDERSON);
        return ab;
    }
    /**
     * Returns an {@code AddressBook} with
     * Students in sorted order lexicographically ascending.
     */
    public static AddressBook getSortedStudentsAscending() {
        AddressBook ab = new AddressBook();
        ab.addPerson(AARON);
        ab.addPerson(ALEX);
        ab.addPerson(ALICE);
        ab.addPerson(BENSON);
        ab.addPerson(CARL);
        ab.addPerson(GEORGE);
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with
     * Students in unsorted order.
     */
    public static AddressBook getUnsortedStudents() {
        AddressBook ab = new AddressBook();
        ab.addPerson(GEORGE);
        ab.addPerson(ALEX);
        ab.addPerson(CARL);
        ab.addPerson(AARON);
        ab.addPerson(BENSON);
        ab.addPerson(ALICE);
        return ab;
    }
    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, ANDERSON,
                BEN, COLIN, CABE, JOHN));
    }
}
