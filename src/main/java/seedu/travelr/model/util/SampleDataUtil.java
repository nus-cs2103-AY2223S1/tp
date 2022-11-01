package seedu.travelr.model.util;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.Travelr;
import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Contains utility methods for populating {@code Travelr} with sample data.
 */
public class SampleDataUtil {
    public static Trip[] getSampleTrips() {
        return new Trip[] {
            new Trip(new Title("Trip to Japan with family"), new Description("Grad Trip with friends!"),
                getEventSet("friends"), new Location("Bristol"), new DateField("11-01-2023")),
            new Trip(new Title("Honeymoon"), new Description("Lorem ipsum dolor sit amet."),
                getEventSet("girlfriend"), new Location("Jeju Island"), new DateField("13-04-2021")),
            new Trip(new Title("Business Trip"), new Description("Consectetur adipiscing elit."),
                getEventSet("neighbours")),
            new Trip(new Title("Solo Trip"), new Description("Sed do eiusmod tempor."),
                getEventSet("family")),
            new Trip(new Title("Backpacking"), new Description("Incididunt ut labore et dolore magna aliqua."),
                getEventSet("classmates")),
            new Trip(new Title("Conference"), new Description("Dolore eu fugiat nulla pariatur."),
                getEventSet("colleagues"), new Location("New York"), new DateField("31-12-2023"))
        };
    }
    
    public static Event[] getSampleEvents() {
        return new Event[] {
                new Event(
                        new Title("Try authentic French Onion Soup"), 
                        new Description("French onion soup is a soup usually based on meat stock and onions")),
                new Event(
                        new Title("Visit the Great Pyramid of Giza"),
                        new Description("It is the oldest and the biggest of the pyramids")),
                new Event(
                        new Title("Sail on Ha Long Bay"),
                        new Description("Frequently included in lists of natural wonders of the world")),
                new Event(
                        new Title("Visit Saint Basils Cathedral"),
                        new Description("Born from a basement served as a basis for nine small churches")),
                new Event(
                        new Title("Visit Easter Island"),
                        new Description("You can not miss the enormous volcanic craters and preserved stone villages")),
        };
    }

    public static ReadOnlyTravelr getSampleTravelr() {
        Travelr sampleAb = new Travelr();
        for (Trip sampleTrip : getSampleTrips()) {
            sampleAb.addTrip(sampleTrip);
            for (Event e : sampleTrip.getEvents()) {
                sampleAb.addEventToAllEventsList(e);
            }
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
