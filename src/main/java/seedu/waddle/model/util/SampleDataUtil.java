package seedu.waddle.model.util;

import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;

/**
 * Contains utility methods for populating {@code Waddle} with sample data.
 */
public class SampleDataUtil {
    public static Itinerary[] getSampleItineraries() {
        return new Itinerary[]{
            new Itinerary(new Description("Graduation Trip"), new Country("Singapore"), new Date("2025-07-30"),
                    new ItineraryDuration("30"), new People("5"), new Budget("1000")),
            new Itinerary(new Description("Winter Trip"), new Country("Sweden"), new Date("2022-11-15"),
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
