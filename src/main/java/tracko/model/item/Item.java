package tracko.model.item;

/**
 * Represents an item entity in the application.
 */
public interface Item {
    public ItemName getItemName();
    public Price getCostPrice();
    public Price getSellPrice();
}
