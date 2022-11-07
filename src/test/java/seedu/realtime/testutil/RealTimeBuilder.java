package seedu.realtime.testutil;

import seedu.realtime.model.RealTime;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Client;

/**
 * A utility class to help with building RealTime objects.
 * Example usage: <br>
 *     {@code realTime rt = new RealTimeBuilder().withClient("John", "Doe").build();}
 */
public class RealTimeBuilder {

    private RealTime realTime;

    public RealTimeBuilder() {
        realTime = new RealTime();
    }

    public RealTimeBuilder(RealTime realTime) {
        this.realTime = realTime;
    }

    /**
     * Adds a new {@code Client} to the {@code RealTime} that we are building.
     */
    public RealTimeBuilder withClient(Client client) {
        realTime.addClient(client);
        return this;
    }

    /**
     * Adds a new {@code Offer} to the {@code RealTime} that we are building.
     */
    public RealTimeBuilder withOffer(Offer offer) {
        realTime.addOffer(offer);
        return this;
    }

    public RealTime build() {
        return realTime;
    }
}
