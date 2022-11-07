package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building SupplyItem objects.
 */
public class SupplyItemBuilder {
    public static final String DEFAULT_NAME = "Flour";
    public static final int CURRENT_STOCK = 100;
    public static final int MIN_STOCK = 10;
    public static final Person SUPPLIER = new PersonBuilder().build();

    private String name;
    private int currentStock;
    private int minStock;
    private Person supplierPerson;
    private Set<Tag> tags;

    /**
     * Creates a {@code SupplyItemBuilder} with the default details.
     */
    public SupplyItemBuilder() {
        name = DEFAULT_NAME;
        currentStock = CURRENT_STOCK;
        minStock = MIN_STOCK;
        supplierPerson = SUPPLIER;
        tags = new HashSet<>();
    }

    /**
     * Sets the {@code name} of the SupplyItem we are building.
     */
    public SupplyItemBuilder withItemName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code currentStock} of the SupplyItem we are building.
     */
    public SupplyItemBuilder withCurrentStock(int currentStock) {
        this.currentStock = currentStock;
        return this;
    }

    /**
     * Sets the {@code minStock} of the SupplyItem we are building.
     */
    public SupplyItemBuilder withMinStock(int minStock) {
        this.minStock = minStock;
        return this;
    }

    /**
     * Sets the {@code supplierPerson} of the SupplyItem we are building.
     */
    public SupplyItemBuilder withSupplierPerson(Person supplierPerson) {
        this.supplierPerson = supplierPerson;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public SupplyItemBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public SupplyItem build() {
        return new SupplyItem(name, currentStock, minStock, supplierPerson, tags);
    }
}
