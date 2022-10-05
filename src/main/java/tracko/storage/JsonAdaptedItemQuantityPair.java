package tracko.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tracko.commons.exceptions.IllegalValueException;
import tracko.model.order.ItemQuantityPair;

public class JsonAdaptedItemQuantityPair {

    private final String item;
    private final String quantity;

    /**
     * Constructs a {@code JsonAdaptedItemQuantityPair} with the given {@code item} and {@code quantity}
     */
    @JsonCreator
    public JsonAdaptedItemQuantityPair(@JsonProperty("item") String item, @JsonProperty("quantity") String quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Converts a given {@code item} and {@code quantity} into this class for Jackson use.
     */
    public JsonAdaptedItemQuantityPair(ItemQuantityPair itemQuantityPair) {
        this.item = itemQuantityPair.getItem();
        this.quantity = itemQuantityPair.getQuantity().toString();
    }

    /**
     * Converts this Jackson
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ItemQuantityPair toModelType() throws IllegalValueException {
        return new ItemQuantityPair(item, Integer.parseInt(quantity));
    }
}
