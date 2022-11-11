package tracko.model.item;

/**
 * Represents an immutable copy of an {@code InventoryItem} at a certain point in time.
 * This class is used to ensure data consistency when marking orders as completed.
 */
public class RecordedItem implements Item {
    private final ItemName itemName;
    private final Price sellPrice;
    private final Price costPrice;

    /**
     * Constructs an a {@code RecordedItem}
     * @param itemName The name of the item
     * @param sellPrice The price that the item was sold at
     * @param costPrice The price that the item cost to produce
     */
    public RecordedItem(ItemName itemName, Price sellPrice, Price costPrice) {
        this.itemName = itemName;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
    }

    /**
     * Constructs a {@code RecordedItem} by copying the data of an {@code InventoryItem}.
     * @param itemToCopy The inventory item to copy
     */
    private RecordedItem(InventoryItem itemToCopy) {
        this.itemName = itemToCopy.getItemName();
        this.sellPrice = itemToCopy.getSellPrice();
        this.costPrice = itemToCopy.getCostPrice();
    }

    /**
     * Returns a copy of the input {@code InventoryItem} in the form of a {@code RecordedItem}.
     * @param itemToCopy The inventory item to copy
     * @return A copy of the input {@code InventoryItem} in the form of a {@code RecordedItem}
     */
    public static RecordedItem getRecordedItemCopy(InventoryItem itemToCopy) {
        return new RecordedItem(itemToCopy);
    }


    @Override
    public ItemName getItemName() {
        return itemName;
    }

    @Override
    public Price getCostPrice() {
        return costPrice;
    }

    @Override
    public Price getSellPrice() {
        return sellPrice;
    }

    /**
     * Returns false regardless of the input {@code Item}. This is because a {@code RecordedItem} is meant to represent
     * a non-unique copy of an {@code InventoryItem}.
     * @param otherItem The input {@code Item}.
     * @return False regardless of the input {@code Item}.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        return false;
    }

    @Override
    public RecordedItem getRecordedItem() {
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecordedItem)) {
            return false;
        }

        RecordedItem otherRecordedItem = (RecordedItem) other;
        return otherRecordedItem.getItemName().equals(getItemName())
                && otherRecordedItem.getCostPrice().equals(getCostPrice())
                && otherRecordedItem.getSellPrice().equals(getSellPrice());
    }
}
