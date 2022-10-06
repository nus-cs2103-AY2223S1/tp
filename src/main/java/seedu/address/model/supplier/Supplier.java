package seedu.address.model.supplier;

import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Supplier in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier {

    // Identity fields
    private final Name name;
    private final Contact contact;
    private final Price price;
    private Item item;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Supplier(Name name, Contact contact,Price price, Item item, Address address, Set<Tag> tags) {
        requireAllNonNull(name, contact, price, item, address, tags);
        this.name = name;
        this.contact = contact;
        this.price = price;
        this.item = item;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public Price getPrice() {
        return price;
    }

    public Item getItem() {
        return item;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both suppliers have the same name.
     * This defines a weaker notion of equality between two suppliers.
     */
    public boolean isSameSupplier(Supplier otherSupplier) {
        if (otherSupplier == this) {
            return true;
        }

        return otherSupplier != null
                && otherSupplier.getName().equals(getName());
    }

    /**
     * Returns true if both suppliers have the same identity and data fields.
     * This defines a stronger notion of equality between two suppliers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Supplier)) {
            return false;
        }

        Supplier otherSupplier = (Supplier) other;
        return otherSupplier.getName().equals(getName())
                && otherSupplier.getContact().equals(getContact())
                && otherSupplier.getPrice().equals(getPrice())
                && otherSupplier.getItem().equals(getItem())
                && otherSupplier.getAddress().equals(getAddress())
                && otherSupplier.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, contact,price,item,address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Contact: ")
                .append(getContact())
                .append("; Price: ")
                .append(getPrice())
                .append("; Item: ")
                .append(getItem())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
