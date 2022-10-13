package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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

    public SupplyItem build() {
        return new SupplyItem(name, currentStock, minStock, supplierPerson, tags);
    }
}
