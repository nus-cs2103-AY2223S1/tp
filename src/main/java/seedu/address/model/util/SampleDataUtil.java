package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.HealthContact;
import seedu.address.model.ReadOnlyHealthContact;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code HealthContact} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");

    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("friends")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet("colleagues", "friends")),
            new Patient(new Name("bernice lim"), new Phone("98765433"), new Email("bernicelim@gmail.com"),
                new Address("Blk 11 Bukit Batok Street 12, #13-05"), EMPTY_REMARK,
                getTagSet("colleagues")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet("neighbours")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet("family")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet("colleagues")),
            new Patient(new Name("alex tan"), new Phone("93341256"), new Email("alextan@gmail.com"),
                new Address("Blk 30 Yishun Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("boss")),
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
            new Appointment(new Name("Alex Yeoh"), new MedicalTest("Blood Test"),
                    new Slot("2022-09-11 10:00"), new Doctor("Dr Tan")),
            new Appointment(new Name("Bernice Yu"), new MedicalTest("X-Ray"),
                    new Slot("2022-09-15 11:00"), new Doctor("Dr Lim")),
            new Appointment(new Name("Charlotte Oliveiro"), new MedicalTest("Blood Test"),
                    new Slot("2022-09-20 12:00"), new Doctor("Dr Tan")),
        };
    }

    public static ReadOnlyHealthContact getSampleHealthContact() {
        HealthContact sampleAb = new HealthContact();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }

        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
