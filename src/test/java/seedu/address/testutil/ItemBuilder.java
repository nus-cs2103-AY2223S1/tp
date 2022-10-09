package seedu.address.testutil;

import seedu.address.model.item.Item;
import seedu.address.model.item.ItemBoughtDate;
import seedu.address.model.item.ItemExpiryDate;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.ItemQuantity;
import seedu.address.model.item.ItemUnit;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Potato";
    public static final String DEFAULT_QUANTITY = "10";
    public static final String DEFAULT_ITEM_UNIT = "kg";
    public static final String DEFAULT_BOUGHT_DATE = "2022-11-11";
    public static final String DEFAULT_EXPIRY_DATE = "2022-11-11";

    // Identity fields
    private ItemName name;
    // Data fields
    private ItemQuantity quantity;
    private ItemUnit unit;
    private ItemBoughtDate boughtDate;
    private ItemExpiryDate expiryDate;
    // TODO: Implement Item with Tags

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new ItemName(DEFAULT_NAME);
        quantity = new ItemQuantity(DEFAULT_QUANTITY);
        unit = new ItemUnit(DEFAULT_ITEM_UNIT);
        boughtDate = new ItemBoughtDate(DEFAULT_BOUGHT_DATE);
        expiryDate = new ItemExpiryDate(DEFAULT_EXPIRY_DATE);
    }

    /**
     * Initializes the ItemBuilder with the data of {@code personToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        quantity = itemToCopy.getQuantity();
        unit = itemToCopy.getUnit();
        boughtDate = itemToCopy.getBoughtDate();
        expiryDate = itemToCopy.getExpiryDate();
    }

    /**
     * Sets the {@code Name} of the {@link Item} that we are building.
     */
    public ItemBuilder withItemName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    /**
     * Sets the item quantity of the {@link Item} that we are building.
     */
    public ItemBuilder withItemQuantity(String quantity) {
        this.quantity = new ItemQuantity(quantity);
        return this;
    }

    /**
     * Sets the item unit of the {@link Item} that we are building.
     */
    public ItemBuilder withItemUnit(String unit) {
        this.unit = new ItemUnit(unit);
        return this;
    }

    /**
     * Sets the item bought date of the {@link Item} that we are building.
     */
    public ItemBuilder withItemBoughtDate(String boughtDate) {
        this.boughtDate = new ItemBoughtDate(boughtDate);
        return this;
    }

    /**
     * Sets the item expiry date of the {@link Item} that we are building.
     */
    public ItemBuilder withItemExpiryDate(String expiryDate) {
        this.expiryDate = new ItemExpiryDate(expiryDate);
        return this;
    }

    // TODO: Implement withItemTags
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@link Item} that we are building.
//     */
//    public ItemBuilder withTags(String... tags) {
//        this.tags = SampleDataUtil.getTagSet(tags);
//        return this;
//    }


    public Item build() {
        return new Item(name, quantity, unit, boughtDate, expiryDate);
    }

}
