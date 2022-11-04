package seedu.address.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.appointment.UpcomingAppointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.FloorNumber;
import seedu.address.model.person.HospitalWing;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientType.PatientTypes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WardNumber;
import seedu.address.model.tag.Medication;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new NextOfKin("Kwee Yeoh, Husband, 91912626"), new PatientType(PatientTypes.INPATIENT),
                    new HospitalWing("South"), new FloorNumber(10), new WardNumber("D312"),
                    getMedicationSet("Paracetamol", "IV Drip"), new ArrayList<>(),
                    new UpcomingAppointment("")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new NextOfKin("Joe Yu, Husband, 82869128"), new PatientType(PatientTypes.OUTPATIENT),
                    null, null, null, getMedicationSet(), new ArrayList<>(),
                    new UpcomingAppointment(LocalDate.now().plusDays(1)
                    .format(DateTimeFormatter.ofPattern("dd-MM-uuuu")))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new NextOfKin("Kenneth Oliverio, Son, 81249567"), new PatientType(PatientTypes.INPATIENT),
                    new HospitalWing("East"), new FloorNumber(1), new WardNumber("T349"),
                    getMedicationSet(), new ArrayList<>(), new UpcomingAppointment(LocalDate.now().plusYears(1)
                    .format(DateTimeFormatter.ofPattern("dd-MM-uuuu")))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new NextOfKin("Candince Yeo, Wife, 87598274"), new PatientType(PatientTypes.OUTPATIENT),
                    null, null, null, getMedicationSet("Ibuprofen"),
                    new ArrayList<>(), new UpcomingAppointment(LocalDate.now().plusDays(5).plusMonths(3)
                    .format(DateTimeFormatter.ofPattern("dd-MM-uuuu")))),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new NextOfKin("Mary Balakrishnan, Cousin, 87259826"), new PatientType(PatientTypes.OUTPATIENT),
                    null, null, null, getMedicationSet("Anarax", "Canabeez"),
                    new ArrayList<>(), new UpcomingAppointment(LocalDate.now().plusDays(20)
                    .format(DateTimeFormatter.ofPattern("dd-MM-uuuu")))),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new NextOfKin("Mary Balakrishnan, Wife, 87259826"), new PatientType(PatientTypes.INPATIENT),
                    new HospitalWing("South"), new FloorNumber(5), new WardNumber("A001"),
                    getMedicationSet(), new ArrayList<>(), new UpcomingAppointment(LocalDate.now().plusYears(20)
                    .format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Medication> getMedicationSet(String... strings) {
        return Arrays.stream(strings)
                .map(Medication::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a PastAppointment created from the data given.
     */
    public static PastAppointment getPastAppointment(String... strings) {
        return new PastAppointment(LocalDate.parse(strings[0], DateTimeFormatter.ofPattern("dd-MM-uuuu")),
                getMedicationSet(strings[1].split(" ")), strings[2]);
    }
}
