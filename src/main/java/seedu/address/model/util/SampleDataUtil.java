package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;
import seedu.address.model.record.RecordList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Birthdate("08-08-1988"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("HighRisk"), getSampleRecordList(), Appointment.of("11-11-2011 1500")),

            new Person(new Name("Bernice Yu"), new Birthdate("04-04-1944"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("GlutenAllergy", "PollenAllergy"), getSampleRecordList(),
                Appointment.of(Appointment.NO_APPOINTMENT_SCHEDULED)),

            new Person(new Name("Charlotte Oliveiro"), new Birthdate("06-06-2006"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("Eczema"), getSampleRecordList(), Appointment.of("01-05-2005 1045")),

            new Person(new Name("David Li"), new Birthdate("01-10-2001"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("Elderly"), getSampleRecordList(),
                Appointment.of(Appointment.NO_APPOINTMENT_SCHEDULED)),

            new Person(new Name("Irfan Ibrahim"), new Birthdate("04-04-1994"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("Diabetic"), getSampleRecordList(), Appointment.of("02-12-2024 1600")),

            new Person(new Name("Roy Balakrishnan"), new Birthdate("09-09-1969"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("DustAllergy"), getSampleRecordList(), Appointment.of("08-03-2023 1200"))
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
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a medication set containing the list of strings given.
     */
    public static Set<Medication> getMedicationSet(String... strings) {
        return Arrays.stream(strings)
                .map(Medication :: of)
                .collect(Collectors.toSet());
    }

    public static RecordList getSampleRecordList() {
        RecordList sampleRecordList = new RecordList();
        sampleRecordList.add(new Record(LocalDateTime.parse("05-06-2022 1200", Record.DATE_FORMAT),
                "tested positive for covid-19"));
        sampleRecordList.add(new Record(LocalDateTime.parse("06-06-2010 1600", Record.DATE_FORMAT),
                "tested positive for H1N1"));
        sampleRecordList.add(new Record(LocalDateTime.parse("20-02-2002 0200", Record.DATE_FORMAT),
                "tested positive for SARS"));

        return sampleRecordList;
    }
}
