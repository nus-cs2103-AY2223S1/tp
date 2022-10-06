package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyPennyWise;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Entry[] getSampleEntries() {
        return new Entry[] {
                new Entry(new Description("Lunch"), new Date("20-01-2022"), new Amount("20"), getTagSet("Lunch")),
                new Entry(new Description("Dinner"), new Date("20-02-2022"), new Amount("30"), getTagSet("Dinner")),
                new Entry(new Description("Breakfast"), new Date("21-01-2022"), new Amount("40"), getTagSet("Breakfast")),
                new Entry(new Description("Paid this guy"), new Date("21-01-2022"), new Amount("0.10"), getTagSet("paid"))
        };
    }

    public static ReadOnlyPennyWise getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Entry sampleEntry : getSampleEntries()) {
            sampleAb.addEntry(sampleEntry);
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
