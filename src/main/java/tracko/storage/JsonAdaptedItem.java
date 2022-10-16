package tracko.storage;

import java.math.BigDecimal;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.items.Description;
import tracko.model.items.Item;
import tracko.model.items.ItemName;
import tracko.model.items.Price;
import tracko.model.items.Quantity;

/**
 * Jackson-friendly version of {@link Item}.
 */
public class JsonAdaptedItem {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String itemName;
    private final Integer quantity;
    private final String description;
    private final BigDecimal sellPrice;
    private final BigDecimal costPrice;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("itemName") String itemName, @JsonProperty("quantity") Integer quantity,
                           @JsonProperty("description") String description,
                           @JsonProperty("sellPrice") BigDecimal sellPrice,
                           @JsonProperty("costPrice") BigDecimal costPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.description = description;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        itemName = source.getItemName().itemName;
        quantity = source.getQuantity().getQuantity();
        description = source.getDescription().value;
        sellPrice = source.getSellPrice().price;
        costPrice = source.getCostPrice().price;
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {

        // item name checks
        if (itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidItemName(itemName)) {
            throw new IllegalValueException(ItemName.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelItemName = new ItemName(itemName);

        // quantity checks
        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        // description checks
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        // sell price checks
        if (sellPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(sellPrice)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Price modelSellPrice = new Price(sellPrice);

        // cost price checks
        if (costPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(costPrice)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Price modelCostPrice = new Price(costPrice);

        return new Item(modelItemName, modelDescription, modelQuantity, new HashSet<>(),
                modelSellPrice, modelCostPrice);
    }
}
