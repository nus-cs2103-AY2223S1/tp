package coydir.testutil;

import static coydir.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_DEPARTMENT_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_EMPLOYEE_ID_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_EMPLOYEE_ID_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_EMPLOYEE_ID_PRITTAM;
import static coydir.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_POSITION_AMY;
import static coydir.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static coydir.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import coydir.model.Database;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPosition("Software Engineer").withDepartment("Information Technology")
            .withPhone("94351253").withRating("3").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPosition("Product Manager").withDepartment("Product Management")
            .withPhone("98765432").withRating("3").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withPosition("Fire Support Officer").withDepartment("Operations")
            .withAddress("wall street").withRating("3").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withPosition("Junior Research Analyst")
            .withDepartment("Research and Development").withAddress("10th street").withTags("friends")
            .withRating("3").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withPosition("Coffee Maker").withDepartment("Operations")
            .withAddress("michegan ave").withRating("3").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withPosition("DevOps Lead").withDepartment("Technology")
            .withAddress("little tokyo").withRating("3").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withPosition("Senior Consultant Specialist")
            .withDepartment("Customer Service").withAddress("4th street").withRating("3").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withPosition("Frontend Engineer")
            .withDepartment("Information Technology").withAddress("little india").withRating("3").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withPosition("Intern").withDepartment("Marketing")
            .withAddress("chicago ave").withRating("3").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withPosition(VALID_POSITION_AMY)
            .withDepartment(VALID_DEPARTMENT_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withPosition(VALID_POSITION_BOB)
            .withDepartment(VALID_DEPARTMENT_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    public static final Person PRITTAM = new PersonBuilder().withName("Prittam Kok")
            .withEmptyPhone()
            .withEmptyEmail().withPosition("Intern").withDepartment("Board of Directors")
            .withEmptyAddress().build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code database} with all the typical persons.
     */
    public static Database getTypicalDatabase() {
        Database ab = new Database();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getRestPersons() {
        return new ArrayList<>(Arrays.asList(HOON, IDA));
    }
}
