package tracko.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import tracko.commons.exceptions.IllegalValueException;
import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An immutable TrackO that is serializable to JSON format.
 */
@JsonRootName(value = "tracko")
public class JsonSerializableTrackO {

    // TODO: add items here

    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackO} with the given orders.
     */
    @JsonCreator
    public JsonSerializableTrackO(@JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        // TODO: add items here (before orders)
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyTrackO} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTrackO(ReadOnlyTrackO source) {
        // TODO: add items here (before orders)
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TrackO instance into the model's {@code TrackO} object
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackO toModelType() throws IllegalValueException {
        // TODO: add items here (before orders)
        TrackO trackO = new TrackO();
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            trackO.addOrder(order);
        }
        return trackO;
    }
}
