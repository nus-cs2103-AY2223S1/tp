package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
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
    private final Set<Tag> tags;

    private final UniqueCommissionList commissions;

    // Optional fields
    private final Address address;

    /**
     * Constructs a Customer.
     *
     * @param builder Instance of CustomerBuilder.
     */
    public Customer(CustomerBuilder builder) {
        name = builder.name;
        phone = builder.phone;
        email = builder.email;
        tags = builder.tags;
        address = builder.address;
        commissions = new UniqueCommissionList();
        builder.commissions.forEach(commission -> {
            commissions.add(new Commission.CommissionBuilder(
                    commission.getTitle(),
                    commission.getFee(),
                    commission.getDeadline(),
                    commission.getCompletionStatus(),
                    commission.getTags()).setIterations(commission.getIterations()).build(this));
        });
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

    /**
     * Returns total revenue generated from the customer.
     */
    public double getRevenue() {
        double revenue = 0;
        for (Commission commission : commissions) {
            revenue += commission.getFee().fee;
        }
        return revenue;
    }

    /**
     * Returns the total number of commissions made by this customer.
     */
    public long getCommissionCount() {
        return commissions.getSize();
    }

    /**
     * Returns the total number of active commissions made by this customer.
     */
    public long getActiveCommissionCount() {
        return commissions.getActiveSize();
    }

    /**
     * Returns the total number of completed commissions made by this customer.
     */
    public long getCompletedCommissionCount() {
        return commissions.getCompletedSize();
    }

    /**
     * Returns the total number of commissions that are in progress made by this customer.
     */
    public long getInProgressCommissionCount() {
        return commissions.getInProgressSize();
    }

    /**
     * Returns the total number of commissions that are not started made by this customer.
     */
    public long getNotStartedCommissionCount() {
        return commissions.getNotStartedSize();
    }

    /**
     * Get last date of customer's commissions.
     */
    public LocalDate getLastDate() {
        return commissions.getLastDate();
    }

    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    public ObservableList<Commission> getCommissionList() {
        return commissions.asUnmodifiableObservableList();
    }

    public int getCommissionsCount() {
        return commissions.getSize();
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
     * Adds commission to current customer.
     *
     * @param commission new commission to add.
     */
    public void addCommission(Commission commission) {
        commissions.add(commission);
    }

    /**
     * Returns true if a commission with the same identity as {@code commission} exists in the customer's
     * commission list.
     */
    public boolean hasCommission(Commission commission) {
        return commissions.contains(commission);
    }

    /**
     * Replaces the given commission {@code target} in the list with {@code editedCommission}.
     * {@code target} must exist in the address book.
     * The commission identity of {@code editedCommission} must not be the same as another existing commission in the
     * customer's commission list.
     */
    public void setCommission(Commission target, Commission editedCommission) {
        commissions.setCommission(target, editedCommission);
    }


    /**
     * Removes {@code key} from this {@code Customer}.
     * {@code key} must exist in the customer's commission list.
     */
    public void removeCommission(Commission key) {
        commissions.remove(key);
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
            && otherCustomer.commissions.equals(commissions);
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
        address.ifPresent(value -> builder.append("; Address: ").append(value));

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Builder class for Customer.
     */
    public static class CustomerBuilder {
        // required parameters
        private final Name name;
        private final Phone phone;
        private final Email email;
        private final Set<Tag> tags = new HashSet<>();
        private final UniqueCommissionList commissions = new UniqueCommissionList();
        // optional parameters
        private Address address;

        /**
         * Builds CustomerBuilder with all required fields.
         */
        public CustomerBuilder(Name name, Phone phone, Email email, Set<Tag> tags) {
            requireAllNonNull(name, phone, email, tags);
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.tags.addAll(tags);
        }

        /**
         * Sets address and returns itself.
         */
        public CustomerBuilder setAddress(Address address) {
            requireNonNull(address);
            this.address = address;
            return this;
        }

        /**
         * Sets commissions and returns itself.
         */
        public CustomerBuilder setCommissions(ObservableList<Commission> commissions) {
            requireNonNull(commissions);
            this.commissions.setCommissions(commissions);
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
