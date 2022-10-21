package tracko.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.item.InventoryList;
import tracko.model.item.Item;
import tracko.model.item.Quantity;
import tracko.model.item.exceptions.ItemNotFoundException;
import tracko.model.order.ItemQuantityPair;

/**
 * Jackson-friendly version of {@link ItemQuantityPair}.
 */
public class JsonAdaptedItemQuantityPair {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pair's %s field is missing!";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item by name '%s' not found in loaded inventory!";

    private final String itemName;
    private final Integer quantity;

    /**
     * Constructs a {@code JsonAdaptedItemQuantityPair} with the given {@code item} and {@code quantity}
     */
    @JsonCreator
    public JsonAdaptedItemQuantityPair(@JsonProperty("item") String itemName,
                                       @JsonProperty("quantity") Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    /**
     * Converts a given {@code item} and {@code quantity} into this class for Jackson use.
     */
    public JsonAdaptedItemQuantityPair(ItemQuantityPair itemQuantityPair) {
        this.itemName = itemQuantityPair.getItemName();
        this.quantity = itemQuantityPair.getQuantity().getQuantity();
    }

    /**
     * Converts this Jackson
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pair.
     */
    public ItemQuantityPair toModelType(InventoryList inventoryList) throws IllegalValueException {

        if (itemName == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT), "Item Name"));
        }

        if (quantity == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT),
                Quantity.class.getSimpleName()));
        }

        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        try {
            Item modelItem = inventoryList.get(itemName);
            return new ItemQuantityPair(modelItem, modelQuantity);
        } catch (ItemNotFoundException e) {
            throw new IllegalValueException(String.format(MESSAGE_ITEM_NOT_FOUND, itemName));
        }
    }
}
