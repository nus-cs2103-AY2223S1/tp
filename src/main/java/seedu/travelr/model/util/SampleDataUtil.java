package seedu.travelr.model.util;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;

import java.util.Arrays;
import java.util.HashSet;
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

        Set<Event> swissEvents = new HashSet<>(Arrays.asList(
                new Event(
                        new Title("Skiing"),
                        new Description("Swiss Alps"))));

        Set<Event> japanEvents = new HashSet<>(Arrays.asList(
                new Event(
                        new Title("Try local Takoyakis"),
                        new Description("Get every flavour possible!")),
                new Event(
                        new Title("Visit a Japanese Shrine"),
                        new Description("Shinto Shrines")),
                new Event(
                        new Title("Visit Ramen Museum"),
                        new Description("Purchase some exotic instant ramen to bring back to Singapore"))));

        Set<Event> parisEvents = new HashSet<>(Arrays.asList(
                new Event(
                        new Title("Visit the Eiffel Tower"),
                        new Description("Take as many pictures as possible!"))));

        Set<Event> chinaEvents = new HashSet<>(Arrays.asList(
                new Event(
                        new Title("See a Beijing Opera Performance"),
                        new Description("Beijing opera is famous for its elaborate masks and costumes worn by the performers")),
                new Event(
                        new Title("Take a Selfie at Tiananmen Square"),
                        new Description("Hanging over the entrance to the Forbidden City at Tiananmen Square is a giant portrait of Mao Zedong")),
                new Event(
                        new Title("Take a Class on Chinese Tea"),
                        new Description("The foundation of interpersonal relationships")),
                new Event(
                        new Title("Walk along the Great Wall of China"),
                        new Description("An astonishing relic of China history"))));

        return new Trip[]{
                new Trip(new Title("Grad Trip to Switzerland"), new Description("Time to enjoy the snow!"),
                        swissEvents, new Location("Switzerland"), new DateField("20-12-2022")),
                new Trip(new Title("Trip to Japan with family"), new Description("Visiting Osaka and Tokyo"),
                        japanEvents, new Location("Japan"), new DateField("10-05-2023")),
                new Trip(new Title("Paris"), new Description("The city of love"),
                        parisEvents, new Location("France"), new DateField("12-12-2023")),
                new Trip(new Title("China cultural Trip"), new Description("Exchange to China with NUS"),
                        chinaEvents, new Location("China"), new DateField("01-01-2024")),
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[]{
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
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEventToBucketListAndAllEventsList(sampleEvent);
        }
        
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
