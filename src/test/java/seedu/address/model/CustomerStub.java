package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

/**
 * A default customer stub that has all of its methods failing.
 */
public class CustomerStub extends Customer {
    /**
     * Constructs a CustomerStub with the given CustomerBuilder.
     */
    public CustomerStub(CustomerBuilder builder) {
        super(builder);
        requireNonNull(builder);
    }

    @Override
    public Name getName() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Phone getPhone() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Email getEmail() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<Address> getAddress() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public double getRevenue() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public long getCommissionCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public long getActiveCommissionCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public long getCompletedCommissionCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public long getInProgressCommissionCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public long getNotStartedCommissionCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public LocalDate getLastDate() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Commission> getCommissionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getCommissionsCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Set<Tag> getTags() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isSameCustomer(Customer otherCustomer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCommission(Commission commission) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCommission(Commission commission) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCommission(Commission target, Commission editedCommission) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeCommission(Commission key) {
        throw new AssertionError("This method should not be called.");
    }
}
