package tracko.model.item;

/**
 * Represents an item entity in the application.
 */
public interface Item {
    ItemName getItemName();
    Price getCostPrice();
    Price getSellPrice();
    boolean isSameItem(Item otherItem);
    RecordedItem getRecordedItem();
}
