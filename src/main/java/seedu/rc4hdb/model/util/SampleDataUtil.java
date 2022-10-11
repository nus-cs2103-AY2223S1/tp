package seedu.rc4hdb.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Resident[] getSampleResidents() {
        return new Resident[] {
            new Resident(
                    new Name("John"), new Phone("90000000"), new Email("john@example.com"), new Room("03-10"),
                    new Gender("M"), new House("D"), new MatricNumber("A0000000A"), getTagSet("BlockHead")
            ),
            new Resident(
                    new Name("Mary"), new Phone("90000001"), new Email("mary@example.com"), new Room("04-05"),
                    new Gender("F"), new House("A"), new MatricNumber("A0000000B"), getTagSet("HouseHead")
            )
        };
    }

    public static ReadOnlyResidentBook getSampleResidentBook() {
        ResidentBook sampleRb = new ResidentBook();
        for (Resident sampleResident : getSampleResidents()) {
            sampleRb.addResident(sampleResident);
        }
        return sampleRb;
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
