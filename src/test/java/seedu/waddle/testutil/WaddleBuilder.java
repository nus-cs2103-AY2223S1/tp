package seedu.waddle.testutil;

import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * A utility class to help with building Waddle objects.
 * Example usage: <br>
 *     {@code Waddle w = new WaddleBuilder().withItineraries("John", "Doe").build();}
 */
public class WaddleBuilder {

    private Waddle waddle;

    public WaddleBuilder() {
        waddle = new Waddle();
    }

    public WaddleBuilder(Waddle waddle) {
        this.waddle = waddle;
    }

    /**
     * Adds a new {@code Itinerary} to the {@code Waddle} that we are building.
     */
    public WaddleBuilder withPerson(Itinerary itinerary) {
        waddle.addItinerary(itinerary);
        return this;
    }

    public Waddle build() {
        return waddle;
    }
}
