package seedu.address.model.customer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.commission.Commission;
import seedu.address.model.commission.UniqueCommissionList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Customer in the address book.
 * Guarantees: apart from address, all details are present and not null, field values are validated, immutable.
 */
public class Customer {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    private final UniqueCommissionList commissions = new UniqueCommissionList();

    // Optional fields
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = null;
        this.tags.addAll(tags);
    }

    /**
     * Initialises Customer without address but with commissions.
     */
    public Customer(Name name, Phone phone, Email email, Set<Tag> tags, UniqueCommissionList commissions) {
        this(name, phone, email, tags);
        this.commissions.setCommissions(commissions);
    }

    /**
     * Initialises Customer without address but with commissions.
     */
    public Customer(Name name, Phone phone, Email email, Set<Tag> tags, List<Commission> commissions) {
        this(name, phone, email, tags);
        this.commissions.setCommissions(commissions);
    }


    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  UniqueCommissionList commissions) {
        this(name, phone, email, address, tags);
        this.commissions.setCommissions(commissions);
    }

    /**
     * Initialises Customer with a list of commissions.
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Commission> commissions) {
        this(name, phone, email, address, tags);
        this.commissions.setCommissions(commissions);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public UniqueCommissionList getCommissions() {
        return commissions;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both customers have the same name.
     * This defines a weaker notion of equality between two customers.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
            && otherCustomer.getName().equals(getName());
    }

    public Customer getClone() {
        return getAddress().map(address -> new Customer(name, phone, email, address, tags, commissions))
                .orElseGet(() -> new Customer(name, phone, email, tags, commissions));
    }

    /**
     * Returns true if both customers have the same identity and data fields.
     * This defines a stronger notion of equality between two customers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
            && otherCustomer.getPhone().equals(getPhone())
            && otherCustomer.getEmail().equals(getEmail())
            && otherCustomer.getAddress().equals(getAddress())
            && otherCustomer.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail());

        Optional<Address> address = getAddress();
        if (address.isPresent()) {
            builder.append("; Address: ").append(getAddress().get());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
