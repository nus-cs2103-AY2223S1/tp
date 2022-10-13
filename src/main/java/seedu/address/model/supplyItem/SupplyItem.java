package seedu.address.model.supplyItem;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;

/**
 * Represents a Supply Item in Salesy
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SupplyItem {

    // Identity fields
    private final String name;

    // Data fields
    private final int currentStock;
    private final int minStock;
    private Person supplier;
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public SupplyItem(String name, int currentStock,int minStock,Person supplier, Set<Tag>tags) {
        requireAllNonNull(name, currentStock,minStock, supplier, tags);
        this.name = name;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.supplier = supplier;
        this.tags.addAll(tags);
    }

    public String getName() {
        return name;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public Name getSupplier() {
     return supplier.getName();
    }

    public Price gePrice() {
        return supplier.getPrice();
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, currentStock,minStock, supplier, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Item: ")
                .append(getName())
                .append("; Supplier: ")
                .append(getSupplier())
                .append(", Price: ")
                .append(gePrice())
                .append(", Stock: ")
                .append(getCurrentStock());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SupplyItem)) {
            return false;
        }

        SupplyItem otherSupplyItem = (SupplyItem) other;
        return otherSupplyItem.getName().equals(getName())
                && otherSupplyItem.getSupplier().equals(getSupplier())
                && otherSupplyItem.gePrice().equals(gePrice())
                && otherSupplyItem.getCurrentStock() == (getCurrentStock())
                && otherSupplyItem.getMinStock() == (getMinStock())
                && otherSupplyItem.getTags().equals(getTags());
    }
}
