package tracko.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.order.ItemQuantityPair;

/**
 * Jackson-friendly version of {@link ItemQuantityPair}.
 */
public class JsonAdaptedItemQuantityPair {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pair's %s field is missing!";

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

        if (item == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT), "Item name"));
        }

        if (quantity == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT), "Quantity"));
        }

        return new ItemQuantityPair(item, Integer.parseInt(quantity));
    }
}
