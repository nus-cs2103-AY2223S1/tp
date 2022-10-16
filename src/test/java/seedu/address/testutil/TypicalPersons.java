package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FLOOR_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOSPITAL_WING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_IBUPROFEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WARD_NUMBER_AMY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withPhone("94351253")
            .withEmail("alice@example.com").withNextOfKin("Charlie Pauline, Brother, 81273645")
            .withPatientType(PatientTypes.OUTPATIENT).withMedication("Ibuprofen").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withPhone("98765432")
            .withEmail("johnd@example.com").withNextOfKin("Daniel Meier, Husband, 81273546")
            .withPatientType(PatientTypes.INPATIENT).withHospitalWing("south").withFloorNumber(1).withWardNumber(26)
            .withMedication("Paracetamol", "Xanax").withPastAppointment("12-06-2022", "Paracetamol Ibuprofen", "Sick")
            .withPastAppointment("14-06-2022", "Chlormine Amoxycillin", "Sick").withUpcomingAppointment("12-06-2022")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withNextOfKin("Charlie Pauline, Brother, 81273645")
            .withPatientType(PatientTypes.OUTPATIENT).build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withNextOfKin("Benson Meier, Husband, 81236848")
            .withPatientType(PatientTypes.INPATIENT).withHospitalWing("south")
            .withFloorNumber(1).withWardNumber(25).build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822245")
            .withEmail("werner@example.com").withNextOfKin("Charles Meyer, Husband, 83646756")
            .withPatientType(PatientTypes.OUTPATIENT).build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824275")
            .withEmail("lydia@example.com").withNextOfKin("Violet Kunz, Daugther, 92725635")
            .withPatientType(PatientTypes.INPATIENT).withHospitalWing("north")
            .withFloorNumber(2).withWardNumber(53).build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824424")
            .withEmail("anna@example.com").withNextOfKin("Charlie Worst, Son, 92347462")
            .withPatientType(PatientTypes.OUTPATIENT).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824245")
            .withEmail("stefan@example.com").withNextOfKin("Nooh Meier, Sister, 92346127")
            .withPatientType(PatientTypes.OUTPATIENT).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821631")
            .withEmail("hans@example.com").withNextOfKin("Nooh Meier, Sister, 92346127")
            .withPatientType(PatientTypes.INPATIENT).withHospitalWing("east")
            .withFloorNumber(1).withWardNumber(24).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withNextOfKin(VALID_NEXT_OF_KIN_AMY)
            .withPatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE_AMY))
            .withHospitalWing(VALID_HOSPITAL_WING_AMY).withFloorNumber(Integer.valueOf(VALID_FLOOR_NUMBER_AMY))
            .withWardNumber(Integer.valueOf(VALID_WARD_NUMBER_AMY)).withMedication(VALID_MEDICATION_IBUPROFEN).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withNextOfKin(VALID_NEXT_OF_KIN_BOB)
            .withPatientType(PatientTypes.parsePatientType(VALID_PATIENT_TYPE_BOB))
            .withMedication().build();

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
