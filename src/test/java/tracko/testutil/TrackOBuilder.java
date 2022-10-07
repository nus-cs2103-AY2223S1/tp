package tracko.testutil;

import tracko.model.TrackO;
import tracko.model.order.Order;

/**
 * A utility class to help with building TrackO objects.
 */
public class TrackOBuilder {

    private TrackO trackO;

    public TrackOBuilder() {
        trackO = new TrackO();
    }

    public TrackOBuilder(TrackO trackO) {
        this.trackO = trackO;
    }

    /**
     * Adds a new {@code Person} to the {@code TrackO} that we are building.
     */
    public TrackOBuilder withOrder(Order order) {
        trackO.addOrder(order);
        return this;
    }

    public TrackO build() {
        return trackO;
    }
}
