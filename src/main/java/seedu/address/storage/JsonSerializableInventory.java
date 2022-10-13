package seedu.address.storage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.item.SupplyItem;

/**
 * An Immutable TaskList that is serializable to JSON format.
 */
public class JsonSerializableInventory {
    private static final String MESSAGE_DUPLICATE_SUPPLY_ITEM = "This SupplyItem already exists in inventory.";
    private final List<JsonAdaptedSupplyItem> supplyItemList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInventory} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableInventory(@JsonProperty("items") List<JsonAdaptedSupplyItem> supplyItems) {
        this.supplyItemList.addAll(supplyItems);
    }

    /**
     * Converts a given {@code ReadOnlyTaskList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskList}.
     */
    public JsonSerializableInventory(ReadOnlyInventory source) {
        supplyItemList.addAll(source.getSupplyItems().stream()
                .map(item -> new JsonAdaptedSupplyItem(item))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this inventory into the model's {@code Inventory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Inventory toModelType() throws IllegalValueException {
        Inventory inventory = new Inventory();
        for (JsonAdaptedSupplyItem jsonAdaptedSupplyItem : supplyItemList) {
            SupplyItem supplyItem = jsonAdaptedSupplyItem.toModelType();
            if (inventory.hasSupplyItem(supplyItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SUPPLY_ITEM);
            }
            inventory.addSupplyItem(supplyItem);
        }
        return inventory;
    }
}
