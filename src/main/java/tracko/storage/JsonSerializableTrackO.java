package tracko.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.item.InventoryItem;
import tracko.model.order.Order;


/**
 * An immutable TrackO that is serializable to JSON format.
 */
@JsonRootName(value = "tracko")
public class JsonSerializableTrackO {

    private final List<JsonAdaptedItem> items = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackO} with the given orders and items.
     */
    @JsonCreator
    public JsonSerializableTrackO(@JsonProperty("items") List<JsonAdaptedItem> items,
                                  @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.items.addAll(items);
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyTrackO} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTrackO(ReadOnlyTrackO source) {

        items.addAll(source.getInventoryList().stream().map(JsonAdaptedItem::new).collect(Collectors.toList()));
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
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            InventoryItem inventoryItem = jsonAdaptedItem.toModelType();
            trackO.addItem(inventoryItem);
        }
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType(trackO.getInventoryReference());
            trackO.addOrder(order);
        }
        return trackO;
    }
}
