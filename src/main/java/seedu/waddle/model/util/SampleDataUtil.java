package seedu.waddle.model.util;

import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.*;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Itinerary[] getSampleItineraries() {
        return new Itinerary[]{
            new Itinerary(new Name("Graduation Trip"), new Country("Singapore"), new Date("2025-07-30"),
                    new ItineraryDuration("30"), new People("5"), new Budget("1000")),
            new Itinerary(new Name("Winter Trip"), new Country("Sweden"), new Date("2022-11-15"),
                    new ItineraryDuration("7"), new People("3"), new Budget("5000")),
        };
    }

    public static ReadOnlyWaddle getSampleWaddle() {
        Waddle sampleWaddle = new Waddle();
        for (Itinerary sampleItinerary : getSampleItineraries()) {
            sampleWaddle.addItinerary(sampleItinerary);
        }
        return sampleWaddle;
    }

}
