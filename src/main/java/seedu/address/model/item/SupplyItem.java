package seedu.address.model.item;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;

/**
 * Represents a Supply Item in Salesy
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SupplyItem {


    public static final String VALIDATION_REGEX = "(?![0-9]+$)[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Item can take any values, and it should not be blank";

    // Identity fields
    private final String name;

    // Data fields
    private final int currentStock;
    private final int minStock;
    private final Person supplier;
    private final Set<Tag> tags = new HashSet<>();


    /*
     * The first character of the item must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

    /**
     * Every field must be present and not null.
     */
    public SupplyItem(String name, int currentStock, int minStock, Person supplier, Set<Tag>tags) {
        requireAllNonNull(name, currentStock, minStock, supplier, tags);
        checkArgument(isValidSupplyItem(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.supplier = supplier;
        this.tags.addAll(tags);
    }

    public static boolean isValidSupplyItem(String name) {
        return name.matches(VALIDATION_REGEX);
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

    public Person getSupplier() {
        return supplier;
    }

    public Price getPrice() {
        return supplier.getPrice();
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, currentStock, minStock, supplier, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Item: ")
                .append(getName())
                .append(", Supplier: ")
                .append(getSupplier())
                .append(", Price: ")
                .append(getPrice())
                .append(", Stock: ")
                .append(getCurrentStock());

        Set<Tag> tags = getSupplier().getTags();
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
                && otherSupplyItem.getCurrentStock() == (getCurrentStock())
                && otherSupplyItem.getMinStock() == (getMinStock())
                && otherSupplyItem.getTags().equals(getTags());
    }
}
