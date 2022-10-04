package seedu.address.model.customer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.commission.Commission;
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
    private final Set<Tag> tags;

    private final Set<Commission> commissions;

    // Optional fields
    private final Address address;

    /**
     * Constructs a Customer.
     * @param builder Instance of CustomerBuilder.
     */
    public Customer(CustomerBuilder builder) {
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
        this.tags = builder.tags;
        this.commissions = builder.commissions;
        this.address = builder.address;
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

    public Set<Commission> getCommissions() {
        return Collections.unmodifiableSet(commissions);
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
            && otherCustomer.getTags().equals(getTags())
            && otherCustomer.getCommissions().equals(getCommissions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, commissions);
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

    /**
     * Copy of customer with new commissions.
     * @param commissions New set of commissions for customer.
     * @return New copied instance of customer.
     */
    public Customer copyWithCommissions(Set<Commission> commissions) {
        CustomerBuilder customerBuilder = new CustomerBuilder(name, phone, email, tags)
                .setCommissions(commissions);
        getAddress().ifPresent(customerBuilder::setAddress);
        return customerBuilder.build();

    }

    /**
     * Builder class for Customer.
     */
    public static class CustomerBuilder {
        // required parameters
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags = new HashSet<>();

        // optional parameters
        private Address address;
        private Set<Commission> commissions = new HashSet<>();

        /**
         * Builds CustomerBuilder with all required fields.
         */
        public CustomerBuilder(Name name, Phone phone, Email email, Set<Tag> tags) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.tags.addAll(tags);
        }

        /**
         * Sets address and returns itself.
         */
        public CustomerBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        /**
         * Sets commissions and returns itself.
         */
        public CustomerBuilder setCommissions(Set<Commission> commissions) {
            this.commissions.clear();
            this.commissions.addAll(commissions);
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }


    }
}
