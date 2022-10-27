package tracko.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.item.InventoryItem;
import tracko.model.item.InventoryList;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.item.RecordedItem;
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
    private final Double costPrice;
    private final Double sellPrice;

    /**
     * Constructs a {@code JsonAdaptedItemQuantityPair} with the given {@code item} and {@code quantity}
     */
    @JsonCreator
    public JsonAdaptedItemQuantityPair(@JsonProperty("item") String itemName,
                                       @JsonProperty("quantity") Integer quantity,
                                       @JsonProperty("costPrice") Double costPrice,
                                       @JsonProperty("sellPrice") Double sellPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
    }

    /**
     * Converts a given {@code item} and {@code quantity} into this class for Jackson use.
     */
    public JsonAdaptedItemQuantityPair(ItemQuantityPair itemQuantityPair) {
        this.itemName = itemQuantityPair.getItemName();
        this.costPrice = itemQuantityPair.getItem().getCostPrice().value;
        this.sellPrice = itemQuantityPair.getItem().getSellPrice().value;
        this.quantity = itemQuantityPair.getQuantity().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code ItemQuantityPair} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pair.
     */
    public ItemQuantityPair toModelType(InventoryList inventoryList) throws IllegalValueException {

        // item name check
        if (itemName == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT),
                ItemName.class.getSimpleName()));
        }

        // quantity check
        if (quantity == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT),
                Quantity.class.getSimpleName()));
        }

        // sell price checks
        if (sellPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Price.class.getSimpleName()));
        }

        // cost price checks
        if (costPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Price.class.getSimpleName()));
        }

        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        try {
            InventoryItem modelInventoryItem = inventoryList.get(itemName);
            // Aside from item name, we want to check that prices are consistent with inventory item
            boolean arePricesConsistent = modelInventoryItem.getCostPrice().value.equals(costPrice)
                    && modelInventoryItem.getSellPrice().value.equals(sellPrice);
            if (!arePricesConsistent) {
                throw new IllegalValueException(String.format(MESSAGE_ITEM_NOT_FOUND, itemName));
            }
            return new ItemQuantityPair(modelInventoryItem, modelQuantity);
        } catch (ItemNotFoundException e) {
            throw new IllegalValueException(String.format(MESSAGE_ITEM_NOT_FOUND, itemName));
        }
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code ItemQuantityPair} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pair.
     */
    public ItemQuantityPair toModelType() throws IllegalValueException {
        // item name checks
        if (itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidItemName(itemName)) {
            throw new IllegalValueException(ItemName.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelItemName = new ItemName(itemName);

        if (quantity == null) {
            throw new IllegalValueException(String.format((MISSING_FIELD_MESSAGE_FORMAT),
                Quantity.class.getSimpleName()));
        }

        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        // sell price checks
        if (sellPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(sellPrice)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelSellPrice = new Price(sellPrice);

        // cost price checks
        if (costPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(costPrice)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelCostPrice = new Price(costPrice);

        RecordedItem modelRecordedItem = new RecordedItem(modelItemName, modelSellPrice, modelCostPrice);
        return new ItemQuantityPair(modelRecordedItem, modelQuantity);
    }
}
