package seedu.foodrem.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.item.Item;

/**
 * An Immutable FoodRem that is serializable to JSON format.
 */
@JsonRootName(value = "foodrem")
class JsonSerializableFoodRem {

    public static final String MESSAGE_DUPLICATE_ITEMS = "Items list contains duplicate item(s).";

    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given items.
     */
    @JsonCreator
    public JsonSerializableFoodRem(@JsonProperty("items") List<JsonAdaptedItem> items) {
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableFoodRem(ReadOnlyFoodRem source) {
        items.addAll(source.getItemList().stream().map(JsonAdaptedItem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalArgumentException if there were any data constraints violated.
     */
    public FoodRem toModelType() throws IllegalArgumentException {
        FoodRem foodRem = new FoodRem();
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            if (foodRem.hasItem(item)) {
                throw new IllegalArgumentException(MESSAGE_DUPLICATE_ITEMS);
            }
            foodRem.addItem(item);
        }
        return foodRem;
    }

}
