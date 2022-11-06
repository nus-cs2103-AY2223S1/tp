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


    // Regex ensures that item cannot be purely numbers or empty,
    // but can consist of both numbers and alphabets.
    public static final String VALIDATION_REGEX = "(?![0-9]+$)[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "Item can take any values, "
           + "but it should not be blank or numbers only";

    // Identity fields
    private final String name;

    // Data fields
    private final int currentStock;
    private final int minStock;
    private final Person supplier;
    private final Set<Tag> tags = new HashSet<>();
    private final int incDecAmount;

    public SupplyItem(String name, int currentStock, int minStock, Person supplier, Set<Tag>tags) {
        this(name, currentStock, minStock, supplier, tags, 1);
    }

    /*
     * The first character of the item must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

    /**
     * Every field must be present and not null.
     */
    public SupplyItem(String name, int currentStock, int minStock, Person supplier, Set<Tag>tags, int incDecAmount) {
        requireAllNonNull(name, supplier, tags);
        checkArgument(isValidSupplyItem(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.supplier = supplier;
        this.tags.addAll(tags);
        this.incDecAmount = incDecAmount;
    }

    public static boolean isValidSupplyItem(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentStock() {
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

    public int getIncDecAmount() {
        return incDecAmount;
    }

    /**
     * Returns true if both items have the same name.
     */
    public boolean isSameItem(SupplyItem otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && (otherItem.getName().equals(getName())
                || otherItem.getSupplier().equals(getSupplier()));
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
                .append(getSupplier().getName())
                .append(", Price: ")
                .append(getPrice())
                .append(", Current Stock: ")
                .append(getCurrentStock())
                .append(", Minimum Stock: ")
                .append(getMinStock());

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
                && otherSupplyItem.getCurrentStock() == (getCurrentStock())
                && otherSupplyItem.getMinStock() == (getMinStock())
                && otherSupplyItem.getTags().equals(getTags());
    }
}
