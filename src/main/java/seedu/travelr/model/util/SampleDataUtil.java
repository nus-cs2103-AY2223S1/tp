package seedu.travelr.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.travelr.model.AddressBook;
import seedu.travelr.model.ReadOnlyAddressBook;
import seedu.travelr.model.tag.Tag;
import seedu.travelr.model.trip.Address;
import seedu.travelr.model.trip.Description;
import seedu.travelr.model.trip.Email;
import seedu.travelr.model.trip.Title;
import seedu.travelr.model.trip.Phone;
import seedu.travelr.model.trip.Trip;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Trip[] getSampleTrips() {
        return new Trip[] {
            new Trip(new Title("Grad Trip"), new Description("Grad Trip with friends!"),
                getTagSet("friends")),
            new Trip(new Title("Honeymoon"), new Description("Lorem ipsum dolor sit amet."),
                getTagSet("colleagues", "friends")),
            new Trip(new Title("Business Trip"), new Description("Consectetur adipiscing elit."),
                getTagSet("neighbours")),
            new Trip(new Title("Solo Trip"), new Description("Sed do eiusmod tempor."),
                getTagSet("family")),
            new Trip(new Title("Backpacking"), new Description("Incididunt ut labore et dolore magna aliqua."),
                getTagSet("classmates")),
            new Trip(new Title("Conference"), new Description("Dolore eu fugiat nulla pariatur."),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Trip sampleTrip : getSampleTrips()) {
            sampleAb.addTrip(sampleTrip);
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
