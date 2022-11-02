package tracko.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.tag.Tag;

/**
 * Jackson-friendly version of {@link InventoryItem}.
 */
public class JsonAdaptedItem {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String itemName;
    private final Integer quantity;
    private final String description;
    private final Double sellPrice;
    private final Double costPrice;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("itemName") String itemName, @JsonProperty("quantity") Integer quantity,
                           @JsonProperty("description") String description,
                           @JsonProperty("sellPrice") Double sellPrice,
                           @JsonProperty("costPrice") Double costPrice,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.description = description;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(InventoryItem source) {
        itemName = source.getItemName().value;
        quantity = source.getTotalQuantity().getValue();
        description = source.getDescription().value;
        sellPrice = source.getSellPrice().value;
        costPrice = source.getCostPrice().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public InventoryItem toModelType() throws IllegalValueException {
        final List<Tag> itemTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            itemTags.add(tag.toModelType());
        }

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

        final Set<Tag> modelTags = new HashSet<>(itemTags);

        return new InventoryItem(modelItemName, modelDescription, modelQuantity, modelTags,
                modelSellPrice, modelCostPrice);
    }
}
