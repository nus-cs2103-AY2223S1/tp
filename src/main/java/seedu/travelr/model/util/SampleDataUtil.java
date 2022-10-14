package seedu.travelr.model.util;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.travelr.model.AddressBook;
import seedu.travelr.model.ReadOnlyAddressBook;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.trip.Trip;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Trip[] getSampleTrips() {
        return new Trip[] {
            new Trip(new Title("Grad Trip"), new Description("Grad Trip with friends!"),
                getEventSet("friends")),
            new Trip(new Title("Honeymoon"), new Description("Lorem ipsum dolor sit amet."),
                getEventSet("colleagues", "friends")),
            new Trip(new Title("Business Trip"), new Description("Consectetur adipiscing elit."),
                getEventSet("neighbours")),
            new Trip(new Title("Solo Trip"), new Description("Sed do eiusmod tempor."),
                getEventSet("family")),
            new Trip(new Title("Backpacking"), new Description("Incididunt ut labore et dolore magna aliqua."),
                getEventSet("classmates")),
            new Trip(new Title("Conference"), new Description("Dolore eu fugiat nulla pariatur."),
                getEventSet("colleagues"))
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
    public static Set<Event> getEventSet(String... strings) {
        return Arrays.stream(strings)
                .map(titles -> new Event(new Title(titles), new Description(EVENT_DESCRIPTION_PLACEHOLDER)))
                .collect(Collectors.toSet());
    }

}
